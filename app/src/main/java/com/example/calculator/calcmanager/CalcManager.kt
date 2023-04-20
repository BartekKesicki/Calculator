package com.example.calculator.calcmanager

import com.example.calculator.actions.CalcOperation

interface CalcManager {
    fun calculate(
        calcOperation: CalcOperation,
        firstNumber: String,
        secondNumber: String
    ): CalcManagerResult
}