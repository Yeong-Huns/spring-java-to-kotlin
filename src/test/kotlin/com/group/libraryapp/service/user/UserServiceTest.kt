package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.transaction.annotation.Transactional

/**
 *packageName    : com.group.libraryapp.service.user
 * fileName       : UserServiceTest
 * author         : Yeong-Huns
 * date           : 2025-02-14
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-14        Yeong-Huns       최초 생성
 */
@Transactional
@SpringBootTest
open class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
) {
    @Test
    @DisplayName("유저 저장기능을 테스트한다.")
    fun saveUserTest() {
        /* given */
        val request = UserCreateRequest("김영훈", null)

        /* when */
        userService.saveUser(request)

        /* then */
        val results = userRepository.findAll()
        assertThat(results)
            .hasSize(1)
            .extracting("name", "age")
            .containsExactly(tuple("김영훈", null))
    }

    @Test
    @DisplayName("저장된 사용자 이름으로 데이터를 조회에 성공한다.")
    fun findByUserNameSuccessTest() {
        /* given */
        val request = UserCreateRequest("이지혜", null)
        userService.saveUser(request)

        /* when */
        val foundUser = userRepository.findByName("이지혜")
            .orElseThrow { IllegalArgumentException("해당 유저를 찾을 수 없습니다.") }

        /* then */
        assertThat(foundUser)
            .extracting("name", "age")
            .containsExactly("이지혜", null)
    }

    @Test
    @DisplayName("사용자 이름으로 데이터 조회에 실패한다.")
    fun findByUserNameFailTest() {
        /* given : 존재하지 않는 사용자 이름 */
        val nonExistName = UserCreateRequest("농심이", null)

        /* when & then */
        assertThatThrownBy {
            userRepository.findByName("농심이")
                .orElseThrow { IllegalArgumentException("해당 이름(${nonExistName.name})의 유저를 찾을 수 없습니다.") }
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("해당 이름(${nonExistName.name})의 유저를 찾을 수 없습니다.")
    }
}