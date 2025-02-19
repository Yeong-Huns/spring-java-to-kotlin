package com.group.libraryapp.repository.user.userLoanHistory

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.type.UserLoanStatus
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
    fun findByBookName(bookName: String): UserLoanHistory?
    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?
    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
    fun countByStatus(status: UserLoanStatus): Long
}