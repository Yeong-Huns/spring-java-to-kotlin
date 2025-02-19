package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
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
interface UserRepository: JpaRepository<User, Long>, UserRepositoryCustom {
    fun findByName(name: String): User?


    /*@Query("select distinct u from User u left join fetch u.userLoanHistories") 동일한데? */
    /*@Query("select u from User u left join u.userLoanHistories")
    fun findAllWithHistories(): List<User>*/
}