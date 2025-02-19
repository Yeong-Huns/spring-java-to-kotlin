package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.User

/**
 *packageName    : com.group.libraryapp.repository.user
 * fileName       : UserRepositoryCustom
 * author         : Yeong-Huns
 * date           : 2025-02-20
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        Yeong-Huns       최초 생성
 */
interface UserRepositoryCustom {
    fun findAllWithHistories(): List<User>
}