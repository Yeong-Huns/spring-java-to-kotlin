package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.repository.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.repository.user.userLoanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.repository.book.BookRepository
import com.group.libraryapp.service.user.UserService
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

    @Autowired
    private lateinit var userService: UserService

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
        bookRepository.save(Book.fixture(name = "해리포터"))
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

    @Test
    @DisplayName("대출중인 책의 갯수를 정상 확인한다.")
    fun countLoanedBookTest() {
        /* given */
        val savedUser = userRepository.save(User("김영훈", null))
        listOf(
            Book.fixture("해리포터", BookType.SOCIETY),
            Book.fixture("나니아연대기", BookType.SOCIETY),
        )
            .map(bookRepository::save)
            .forEach(savedUser::loanBook)
        /* when */
        val count = bookService.countLoanedBook()

        /* then */
        assertThat(count).isEqualTo(2)
    }

    @Test
    @DisplayName("분야별 책 권수를 정상 확인한다.")
    fun getBookStatisticsTest(){
        /* given */
        bookRepository.saveAll(listOf(
            Book.fixture("해리포터", BookType.SOCIETY),
            Book.fixture("클린코드", BookType.COMPUTER),
            Book.fixture("이펙티브자바", BookType.COMPUTER),
        ))

        /* when */
        val results = bookService.getBookStatistics()

        /* then */
        assertThat(results).hasSize(2)
        assertCount(results, BookType.COMPUTER, 2)
        assertCount(results, BookType.SOCIETY, 1)
    }
}

private fun assertCount(results: List<BookStatResponse>, type: BookType, count: Long) {
    assertThat(results.first { it.type == type}.count).isEqualTo(count)
}