package com.group.libraryapp.dto.book.response

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.type.UserLoanStatus

/**
 *packageName    : com.group.libraryapp.dto.book.response
 * fileName       : BookHistoryResponse
 * author         : Yeong-Huns
 * date           : 2025-02-18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        Yeong-Huns       최초 생성
 */
data class BookHistoryResponse(
    val name: String, // 책이름
    val isReturn: Boolean,
){
    companion object{
        fun fromUserLoanHistory(history: UserLoanHistory): BookHistoryResponse{
            return BookHistoryResponse(
                name = history.bookName,
                isReturn = history.status == UserLoanStatus.RETURNED
            )
        }
    }
}