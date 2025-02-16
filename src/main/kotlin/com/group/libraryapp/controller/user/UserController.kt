package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 *packageName    : com.group.libraryapp.controller.user
 * fileName       : UserController
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun saveUser(@RequestBody userCreateRequest: UserCreateRequest) =
        userService.saveUser(userCreateRequest)


    @GetMapping
    fun getUsers(): ResponseEntity<List<UserResponse>> =
        ResponseEntity.ok(userService.getUsers())


    @PutMapping
    fun updateUserName(@RequestBody userUpdateRequest: UserUpdateRequest) =
        userService.updateUserName(userUpdateRequest)


    @DeleteMapping
    fun deleteUsers(@RequestParam name: String) =
        userService.deleteUser(name)
}