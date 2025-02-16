package com.group.libraryapp.repository.user.userLoanHistory

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 *packageName    : com.group.libraryapp.repository.user.userLoanHistory
 * fileName       : UserLoanHistoryRepository
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
interface UserLoanHistoryRepository: JpaRepository<UserLoanHistory, Long> {
    fun findByBookNameAndIsReturn(bookName: String, isReturn: Boolean): UserLoanHistory?
}