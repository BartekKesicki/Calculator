package com.example.calculator.state

import com.example.calculator.actions.CalcOperation

data class CalcState(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val calcOperation: CalcOperation? = null
) {
}