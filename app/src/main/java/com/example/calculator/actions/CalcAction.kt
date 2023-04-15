package com.example.calculator.actions

sealed class CalcAction {
    class Number(val value: Int): CalcAction()
    class Operation(val operation: CalcOperation) : CalcAction()
    object Clear: CalcAction()
    object Delete: CalcAction()
    object PutDecimal: CalcAction()
    object Calculate: CalcAction()
}