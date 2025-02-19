package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 *packageName    : com.group.libraryapp.repository.book
 * fileName       : BookQueryDslRepository
 * author         : Yeong-Huns
 * date           : 2025-02-20
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        Yeong-Huns       최초 생성
 */
@Component
class BookQueryDslRepository(private val queryFactory: JPAQueryFactory) {
    fun getStats(): List<BookStatResponse>{
        return queryFactory.select(
            Projections.constructor(
                BookStatResponse::class.java,
                book.type,
                book.id.count()
            )
        )
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}