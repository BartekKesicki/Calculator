package com.example.calculator.state

import com.example.calculator.actions.CalcOperation

class CalcState(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val calcOperation: CalcOperation? = null
) {
}