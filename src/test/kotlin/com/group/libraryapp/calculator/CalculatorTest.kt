package com.group.libraryapp.calculator

import java.lang.Exception

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}

class CalculatorTest {

    fun addTest() {
        // given
        val calculator = Calculator(3)

        // when
        calculator.add(5)

        // then
        if (calculator.number != 8) {
            throw IllegalStateException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }

    fun minusTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.minus(5)

        // then
        if (calculator.number != 1) {
            throw IllegalStateException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }

    fun multiplyTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.multiply(5)

        // then
        if (calculator.number != 30) {
            throw IllegalStateException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }

    fun divideTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.divide(2)

        // then
        if (calculator.number != 3) {
            throw IllegalStateException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }

    fun divideExceptionTest() {
        // given
        val calculator = Calculator(6)

        // when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            // then 성공!
            return
        } catch (e: Exception) {
            // then 실패
            throw IllegalStateException("기대한 타입의 예외가 발생하지 않았습니다.")
        }
        throw IllegalStateException("기대한 예외가 발생하지 않았습니다.")
    }
}