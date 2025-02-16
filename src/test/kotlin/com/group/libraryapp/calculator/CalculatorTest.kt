package com.group.libraryapp.calculator

import org.hibernate.boot.model.naming.IllegalIdentifierException

/**
 *packageName    : com.group.libraryapp.calculator
 * fileName       : CalculatorTest
 * author         : Yeong-Huns
 * date           : 2025-02-12
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-12        Yeong-Huns       최초 생성
 */
class CalculatorTest {
    fun addTest(){
        /* given */
        val calculator = Calculator(5)

        /* when */
        calculator.plus(3)

        /* then */
        // val expectedCalculator = Calculator(8) data class 사용시
        if(calculator.number != 8){
            throw IllegalIdentifierException("5 더하기 3은 8이여야 합니다.")
        }
    }

    fun minusTest(){
        /* given */
        val calculator = Calculator(5)

        /* when */
        calculator.minus(3)

        /* then */
        // val expectedCalculator = Calculator(8) data class 사용시
        if(calculator.number != 2){
            throw IllegalIdentifierException("5 빼기 3은 2이여야 합니다.")
        }
    }

    fun multiplyTest(){
        /* given */
        val calculator = Calculator(5)

        /* when */
        calculator.multiply(3)

        /* then */
        // val expectedCalculator = Calculator(8) data class 사용시
        if(calculator.number != 15){
            throw IllegalIdentifierException("5 곱하기 3은 15이여야 합니다.")
        }
    }

    fun divideTest(){
        /* given */
        val calculator = Calculator(6)

        /* when */
        calculator.div(3)

        /* then */
        // val expectedCalculator = Calculator(8) data class 사용시
        if(calculator.number != 2){
            throw IllegalIdentifierException("6 나누기 3은 2이여야 합니다.")
        }
    }

    fun divisionByZeroTest(){
        /* given */
        val calculator = Calculator(6)

        /* when */
        try{
            calculator.div(0)
        } catch (e: IllegalArgumentException){
            // 테스트 성공
            if(e.message != "Division by zero"){
                throw IllegalIdentifierException("원하는 에러 메세지가 아닙니다.")
            }
            return
        } catch (e: Exception){
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")

        /* then */
        // val expectedCalculator = Calculator(8) data class 사용시
        if(calculator.number != 2){
            throw IllegalIdentifierException("6 나누기 3은 2이여야 합니다.")
        }
    }
}

fun main(){
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divisionByZeroTest()
}