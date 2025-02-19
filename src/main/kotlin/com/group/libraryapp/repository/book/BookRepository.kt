package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

/**
 *packageName    : com.group.libraryapp.repository.book
 * fileName       : BookRepository
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName: String): Book?

    @Query("select new com.group.libraryapp.dto.book.response.BookStatResponse(b.type, count(b.id)) from Book b group by b.type")
    fun getStats(): List<BookStatResponse>
}