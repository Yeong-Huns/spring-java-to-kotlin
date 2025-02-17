package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.type.UserLoanStatus
import jakarta.persistence.*

/**
 *packageName    : com.group.libraryapp.domain.user.loanhistory
 * fileName       : UserLoanHistory
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
@Entity
class UserLoanHistory (
    @ManyToOne
    val user: User,

    val bookName: String,

    @Enumerated(EnumType.STRING)
    var status : UserLoanStatus = UserLoanStatus.LOANED,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
){
    fun doReturn(){
        this.status = UserLoanStatus.RETURNED
    }

    companion object {
        fun fixture(
            user: User,
            bookName: String = "해리포터",
            status: UserLoanStatus = UserLoanStatus.LOANED,
            id: Long? = null,
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookName = bookName,
                status = status,
                id = id,
            )
        }
    }
}