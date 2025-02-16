package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.repository.user.UserRepository
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
            .map(::UserResponse)
            .toList()
    }

    @Transactional
    fun updateUserName(userUpdateRequest: UserUpdateRequest){
        userRepository.findById(userUpdateRequest.id)
            .orElseThrow{IllegalArgumentException("해당하는 사용자가 존재하지 않습니다.")}
            .updateName(userUpdateRequest.name)
    }

    @Transactional
    fun deleteUser(name: String){
        userRepository.findByName(name)
            .orElseThrow{IllegalArgumentException("해당하는 이름의 사용자가 존재하지 않습니다.")}
            .let(userRepository::delete)
    }
}