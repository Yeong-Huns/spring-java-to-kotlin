package com.group.librayapp.calculator

/**
 *packageName    : com.group.librayapp.calculator
 * fileName       : Calculator
 * author         : Yeong-Huns
 * date           : 2025-02-12
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-12        Yeong-Huns       최초 생성
 */
class Calculator(private var _number: Int) {

    /* getter 만들어주기 */
    val number: Int get() = this._number

    fun plus(operand: Int){
        this._number += operand
    }

    fun minus(operand: Int){
        this._number -= operand
    }

    fun multiply (operand: Int){
        this._number *= operand
    }

    fun div (operand: Int){
        if(operand == 0) throw IllegalArgumentException("Division by zero")
        this._number /= operand
    }
}