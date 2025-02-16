package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 *packageName    : com.group.libraryapp.repository.user
 * fileName       : UserRepository
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
interface UserRepository: JpaRepository<User, Long> {
    fun findByName(name: String): User?
    fun existsByName(name: String): Boolean
}