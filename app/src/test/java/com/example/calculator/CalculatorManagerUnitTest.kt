package com.example.calculator

import com.example.calculator.actions.CalcOperation
import com.example.calculator.calcmanager.CalcManager
import com.example.calculator.calcmanager.CalcManagerImpl
import com.example.calculator.calcmanager.CalcManagerResultMode
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorManagerUnitTest {

    val FIRST_NUMBER = 10.0
    val SECOND_NUMBER = 5.0
    val SECOND_NUMBER_FOR_INCORRECT_DIV = 0
    val EXPECTED_ADD_RESULT = 15.0
    val EXPECTED_SUB_RESULT = 5.0
    val EXPECTED_MUL_RESULT = 50.0
    val EXPECTED_DIV_RESULT = 2.0
    lateinit var calcManager: CalcManager

    @Before
    fun initData() {
        calcManager = CalcManagerImpl()
    }

    @Test
    fun addition_is_correct_should_be_successful() {
        val result = calcManager.calculate(
            calcOperation = CalcOperation.Add,
            firstNumber = FIRST_NUMBER.toString(),
            secondNumber = SECOND_NUMBER.toString()
        )
        assertEquals(result.value.toDouble(), EXPECTED_ADD_RESULT)
    }

    @Test
    fun sub_is_correct_should_be_successful() {
        val result = calcManager.calculate(
            calcOperation = CalcOperation.Sub,
            firstNumber = FIRST_NUMBER.toString(),
            secondNumber = SECOND_NUMBER.toString()
        )
        assertEquals(result.value.toDouble(), EXPECTED_SUB_RESULT)
    }

    @Test
    fun mul_is_correct_should_be_successful() {
        val result = calcManager.calculate(
            calcOperation = CalcOperation.Mul,
            firstNumber = FIRST_NUMBER.toString(),
            secondNumber = SECOND_NUMBER.toString()
        )
        assertEquals(result.value.toDouble(), EXPECTED_MUL_RESULT)
    }

    @Test
    fun div_is_correct_should_be_successful() {
        val result = calcManager.calculate(
            calcOperation = CalcOperation.Div,
            firstNumber = FIRST_NUMBER.toString(),
            secondNumber = SECOND_NUMBER.toString()
        )
        assertEquals(result.value.toDouble(), EXPECTED_DIV_RESULT)
    }

    @Test
    fun div_is_incorrect_should_be_error_mode() {
        val result = calcManager.calculate(
            calcOperation = CalcOperation.Div,
            firstNumber = FIRST_NUMBER.toString(),
            secondNumber = SECOND_NUMBER_FOR_INCORRECT_DIV.toString()
        )
        assertEquals(result.mode, CalcManagerResultMode.error)
    }
}