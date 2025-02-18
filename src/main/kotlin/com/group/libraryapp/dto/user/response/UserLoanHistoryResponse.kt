package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.dto.book.response.BookHistoryResponse

/**
 *packageName    : com.group.libraryapp.dto.user.response
 * fileName       : UserHistoryResponse
 * author         : Yeong-Huns
 * date           : 2025-02-17
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-17        Yeong-Huns       최초 생성
 */
data class UserLoanHistoryResponse (
    val name: String, // 유저이름
    val books: List<BookHistoryResponse>
) {
    companion object {
        fun fromUser(user: User): UserLoanHistoryResponse {
            return UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistories.map (BookHistoryResponse::fromUserLoanHistory)
            )
        }
    }
}

