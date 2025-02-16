package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.repository.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.repository.user.userLoanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.repository.book.BookRepository
import com.group.libraryapp.type.BookType
import com.group.libraryapp.type.UserLoanStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

/**
 * packageName    : com.group.libraryapp.service.book
 * fileName       : BookServiceTest
 * author         : Yeong-Huns
 * date           : 2025-02-15
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-15        Yeong-Huns       최초 생성
 */
@Transactional
@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    @Test
    @DisplayName("책을 저장하는데 성공한다.")
    fun saveBook() {
        /* given */
        val saveRequest = BookRequest(name = "나니아 연대기", type = BookType.COMPUTER)

        /* when */
        bookService.saveBook(saveRequest)

        /* then */
        val result = bookRepository.findAll()
        assertThat(result).hasSize(1)
            .first()
            .extracting("name", "type")
            .containsExactly("나니아 연대기", BookType.COMPUTER)
    }

    @Test
    @DisplayName("책을 빌리는데 성공한다.")
    fun loanBookTest() {
        /* given */
        bookRepository.save(Book.fixture(name= "해리포터"))
        val savedUser = userRepository.save(User("강완수", null))
        val request = BookLoanRequest("강완수", "해리포터")

        /* when */
        bookService.loanBook(request)

        /* then */
        val result = userLoanHistoryRepository.findAll()
        assertThat(result).hasSize(1)
            .first()
            .extracting("user", "bookName", "status")
            .containsExactly(savedUser, "해리포터", UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("책이 대출되어 있다면, 책을 빌리는데 실패한다.")
    fun loanBookFailTest() {
        /* given */
        bookRepository.save(Book.fixture(name = "해리포터"))
        val savedUser = userRepository.save(User("강완수", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(savedUser, "해리포터"))
        val request = BookLoanRequest("강완수", "해리포터")

        /* when & then */
        assertThatThrownBy { bookService.loanBook(request) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("해당 도서는 대출 중입니다.")
    }

    @Test
    @DisplayName("책을 반납하는데 성공한다.")
    fun returnBook() {
        /* given */
        val book = bookRepository.save(Book.fixture("해리포터"))
        val savedUser = userRepository.save(User("강완수", null))
        savedUser.loanBook(book)
        val request = BookReturnRequest("강완수", "해리포터")

        /* when */
        bookService.returnBook(request)

        /* then */
        val result = userLoanHistoryRepository.findAll()
        assertThat(result).hasSize(1)
            .first()
            .extracting("status")
            .isEqualTo(UserLoanStatus.RETURNED)
    }
}