package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 *packageName    : com.group.libraryapp
 * fileName       : JUnitTest
 * author         : Yeong-Huns
 * date           : 2025-02-12
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-12        Yeong-Huns       최초 생성
 */
class JUnitTest {

    companion object{
        @JvmStatic
        @BeforeAll
        fun beforeAll(){
            println("📌모든 테스트 시작 전")
        }

        @JvmStatic
        @AfterAll
        fun afterAll(){
            println("😊모든 테스트 종료 후")
        }
    }

    @BeforeEach
    fun beforeEach(){
        println("📒각 테스트 시작 전")
    }

    @AfterEach
    fun afterEach(){
        println("♥️각 테스트 종료 후")
    }

    @Test
    fun test1(){
        println("테스트 1")
    }

    @Test
    fun test2(){
        println("테스트 2")
    }
}