package com.group.libraryapp.repository.user.userLoanHistory

import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.type.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 *packageName    : com.group.libraryapp.repository.user.userLoanHistory
 * fileName       : UserLoanHistoryQueryDslRepository
 * author         : Yeong-Huns
 * date           : 2025-02-20
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        Yeong-Huns       최초 생성
 */
@Component
class UserLoanHistoryQueryDslRepository(private val queryFactory: JPAQueryFactory) {
    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory? {
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
                status?.let{ userLoanHistory.status.eq(it) }
            )
            .limit(1)
            .fetchOne()
    }

    fun count(status: UserLoanStatus): Long {
        return queryFactory.select(userLoanHistory.id.count())
            .from(userLoanHistory)
            .where(userLoanHistory.status.eq(status))
            .fetchOne() ?: 0L
    }
}