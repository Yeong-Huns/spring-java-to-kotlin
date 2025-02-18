package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.dto.book.response.BookHistoryResponse
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.repository.user.UserRepository
import com.group.libraryapp.type.UserLoanStatus
import com.group.libraryapp.uitl.fail
import com.group.libraryapp.uitl.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 *packageName    : com.group.libraryapp.service.user
 * fileName       : UserService
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
@Service
class UserService (
    private val userRepository: UserRepository,
){
    @Transactional
    fun saveUser(request: UserCreateRequest){
        val newUser = User(name = request.name, age = request.age)
        userRepository.save(newUser)
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse>{
        return userRepository.findAll()
            .asSequence()
            .map(UserResponse::of)
            .toList()
    }

    @Transactional
    fun updateUserName(userUpdateRequest: UserUpdateRequest){
        userRepository.findByIdOrThrow(userUpdateRequest.id)
            ?.updateName(userUpdateRequest.name)
    }

    @Transactional
    fun deleteUser(name: String){
        userRepository.findByName(name)?.let(userRepository::delete)
            ?: fail("해당하는 이름의 사용자가 존재하지 않습니다.")
    }

    @Transactional(readOnly = true)
    fun getUserLoanHistories() : List<UserLoanHistoryResponse>{
        return userRepository.findAllWithHistories().map (UserLoanHistoryResponse::fromUser)
    }
}