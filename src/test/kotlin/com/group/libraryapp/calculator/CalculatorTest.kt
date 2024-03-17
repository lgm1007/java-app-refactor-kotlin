package com.group.libraryapp.calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
}

class CalculatorTest {

    fun addTest() {
        // given
        val calculator = Calculator(3)

        // when
        calculator.add(5)

        // then
        if (calculator.number != 8) {
            throw IllegalArgumentException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }

    fun minusTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.minus(5)

        // then
        if (calculator.number != 1) {
            throw IllegalArgumentException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }

    fun multiplyTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.multiply(5)

        // then
        if (calculator.number != 30) {
            throw IllegalArgumentException("기대한 테스트 결과와 일치하지 않습니다.")
        }
    }
}