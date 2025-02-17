package com.group.libraryapp.service.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.repository.user.UserRepository
import com.group.libraryapp.repository.user.userLoanHistory.UserLoanHistoryRepository
import com.group.libraryapp.uitl.fail
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
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
        userRepository.saveAll(
            listOf(User("이지혜", 20), User("유중혁", null))
        )

        /* when */
        val results = userService.getUsers()

        /* then */
        assertThat(results).hasSize(2)
            .extracting("name", "age")
            .containsExactly(tuple("이지혜", 20), tuple("유중혁", null))
    }

    @Test
    @DisplayName("사용자 이름으로 데이터 조회에 실패한다.")
    fun findByUserNameFailTest() {
        /* given : 존재하지 않는 사용자 이름 */
        val nonExistName = UserCreateRequest("농심이", null)

        /* when & then */
        assertThatThrownBy {
            userRepository.findByName("농심이")
                ?: fail("해당 이름(${nonExistName.name})의 유저를 찾을 수 없습니다.")
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("해당 이름(${nonExistName.name})의 유저를 찾을 수 없습니다.")
    }

    @Test
    @DisplayName("유저 이름 업데이트에 성공한다.")
    fun updateUserNameTest() {
        /* given */
        val savedUser = userRepository.save(User("김남운", null))
        val request = UserUpdateRequest(savedUser.id!!, "이현성")

        /* when */
        userService.updateUserName(request)

        /* then */
        val result = userRepository.findAll()[0]
        assertThat(result).extracting("name").isEqualTo("이현성")

    }

    @Test
    @DisplayName("유저 삭제 테스트에 성공한다.")
    fun deleteUserTest() {
        /* given */
        userRepository.save(User("공필두", 30))

        /* when */
        userService.deleteUser("공필두")

        /* then */
        val result = userRepository.findAll()
        assertThat(result).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다")
    fun getUserLoanHistories() {
        /* given */
        userRepository.save(User("영훈", null))

        /* when */
        val result = userService.getUserLoanHistories()

        /* then */
        assertThat(result).hasSize(1)
            .first()
            .extracting("name", "books")
            .containsExactly("영훈", emptyList<Book>())
    }


    @Test
    @DisplayName("대출 기록이 많은 유저의 응답을 확인한다.")
    fun getUserLoanHistories2() {
        /* given */
        val savedUser = userRepository.save(User("영훈", null))

        listOf(
            Book.fixture("클린코드"),
            Book.fixture("헤드퍼스트자바"),
            Book.fixture("이펙티브자바"),
            Book.fixture("이펙티브자바스크립트"),
            Book.fixture("해리포터"),
            Book.fixture("나니아 연대기"),
        ).forEach(savedUser::loanBook)

        /* when */
        val result = userService.getUserLoanHistories()

        /* then */
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("영훈")
        assertThat(result[0].books).hasSize(6)
        assertThat(result[0].books).extracting("name")
            .containsExactlyInAnyOrder("클린코드", "헤드퍼스트자바", "이펙티브자바", "이펙티브자바스크립트", "해리포터", "나니아 연대기")
    }

    /* 이런 테스트 코드는 지양하자 (작은 단위로 나눠서 2개를 만드는게 낫다 )*/
    /*@Test
    @DisplayName("대출 기록이 없는 유저와 많은 유저 모두 확인한다")
    fun getUserLoanHistories3() {
        *//* given *//*
        val savedUsers = userRepository.saveAll(listOf(
            User("순재", null),
            User("덕선", null)
        ))

        listOf(
            Book.fixture("클린코드"),
            Book.fixture("헤드퍼스트자바"),
            Book.fixture("이펙티브자바"),
            Book.fixture("이펙티브자바스크립트"),
            Book.fixture("해리포터"),
            Book.fixture("나니아 연대기"),
        ).forEach { savedUsers[0].loanBook(it) }

        *//* when *//*
        val result = userService.getUserLoanHistories()

        *//* then *//*
        assertThat(result).hasSize(2)
        val userA = result.first{ it.name == "순재" }
        assertThat(userA.books).hasSize(6)
            .extracting("name")
            .containsExactlyInAnyOrder("클린코드", "헤드퍼스트자바", "이펙티브자바", "이펙티브자바스크립트", "해리포터", "나니아 연대기")

        val userB = result.first { it.name == "덕선" }
        assertThat(userB.books).isEmpty()
    }*/
}