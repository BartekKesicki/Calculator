package com.example.calculator.calcmanager

data class CalcManagerResult(
    val mode: CalcManagerResultMode,
    val value: String = ""
)

enum class CalcManagerResultMode {
    number, error, none
}