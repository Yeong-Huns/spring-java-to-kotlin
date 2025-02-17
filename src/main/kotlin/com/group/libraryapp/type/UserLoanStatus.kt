package com.group.libraryapp.type

/**
 *packageName    : com.group.libraryapp.type
 * fileName       : UserLoanStatus
 * author         : Yeong-Huns
 * date           : 2025-02-17
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-17        Yeong-Huns       최초 생성
 */
enum class UserLoanStatus {
    RETURNED, /* 반납 되어 있는 상태 */
    LOANED, /* 대출 중인 상태 */
}