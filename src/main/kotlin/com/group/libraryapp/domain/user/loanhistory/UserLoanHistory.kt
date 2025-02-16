package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

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

    var isReturn : Boolean,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
){
    fun doReturn(){
        this.isReturn = true
    }
}