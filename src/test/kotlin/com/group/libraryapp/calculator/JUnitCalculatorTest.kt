package com.group.libraryapp.calculator

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

/**
 *packageName    : com.group.libraryapp.calculator
 * fileName       : JUnitCalculatorTest
 * author         : Yeong-Huns
 * date           : 2025-02-12
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-12        Yeong-Huns       최초 생성
 */
class JUnitCalculatorTest {

    @Test
    fun addTest() {
        /* given */
        val calculator = Calculator(5)

        /* when */
        calculator.plus(3)

        /* then */
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest() {
        /* given */
        val calculator = Calculator(5)

        /* when */
        calculator.minus(3)

        /* then */
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiTest() {
        /* given */
        val calculator = Calculator(5)

        /* when */
        calculator.multiply(3)

        /* then */
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divTest() {
        /* given */
        val calculator = Calculator(6)

        /* when */
        calculator.div(3)

        /* then */
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divisionByZeroTest() {
        /* given */
        val calculator = Calculator(5)

        /* when */
        /* then */
        assertThatThrownBy { calculator.div(0) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Division by zero")
    }
}