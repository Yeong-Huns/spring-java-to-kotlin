package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory

/**
 *packageName    : com.group.libraryapp.repository.user
 * fileName       : UserRepositoryCustomImpl
 * author         : Yeong-Huns
 * date           : 2025-02-20
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        Yeong-Huns       최초 생성
 */
class UserRepositoryCustomImpl(private val queryFactory: JPAQueryFactory) : UserRepositoryCustom {
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory) //.leftJoin(user.userLoanHistories)
            .on(userLoanHistory.user.id.eq(user.id))
            .fetchJoin()
            .fetch()
    }
}