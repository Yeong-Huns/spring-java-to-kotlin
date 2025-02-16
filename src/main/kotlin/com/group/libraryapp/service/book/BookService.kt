package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.repository.book.BookRepository
import com.group.libraryapp.repository.user.UserRepository
import com.group.libraryapp.repository.user.userLoanHistory.UserLoanHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *packageName    : com.group.libraryapp.service.book
 * fileName       : BookService
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {
    @Transactional
    fun saveBook(saveRequest: BookRequest) {
        bookRepository.save(Book(saveRequest.name))
    }

    @Transactional
    fun loanBook(bookLoanRequest: BookLoanRequest) {
        bookRepository.findByName(bookLoanRequest.bookName)
            .orElseThrow { IllegalArgumentException("해당하는 이름의 도서를 찾을 수 없습니다.") }
            .also {
                if (userLoanHistoryRepository.findByBookNameAndIsReturn(bookLoanRequest.bookName, false) != null) {
                    throw IllegalArgumentException("해당 도서는 대출 중입니다.")
                }
            }
            .let { book ->
                userRepository.findByName(bookLoanRequest.userName)
                    .orElseThrow { IllegalArgumentException("해당하는 사용자를 찾을 수 없습니다.") }
                    .loanBook(book)
            }
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        userRepository.findByName(request.userName)
            .orElseThrow { IllegalArgumentException("해당하는 이름의 도서를 찾을 수 없습니다.") }
            .returnBook(request.bookName)
    }
}