package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {
    @Test
    fun addTest() {
        // given
        val calculator = Calculator(3)

        // when
        calculator.add(5)

        // then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.minus(5)

        // then
        assertThat(calculator.number).isEqualTo(1)
    }

    @Test
    fun multiplyTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.multiply(5)

        // then
        assertThat(calculator.number).isEqualTo(30)
    }

    @Test
    fun divideTest() {
        // given
        val calculator = Calculator(6)

        // when
        calculator.divide(2)

        // then
        assertThat(calculator.number).isEqualTo(3)
    }

    @Test
    fun divideExceptionTest() {
        // given
        val calculator = Calculator(6)

        // when & then
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }
    }

    @Test
    fun divideExceptionMessageTest() {
        // given
        val calculator = Calculator(6)

        // when
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message

        // then
        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
    }
}