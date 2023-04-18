package com.example.calculator.calcmanager

import com.example.calculator.actions.CalcOperation
import com.example.calculator.common.AppConstants.Companion.ERROR
import java.lang.Exception
import javax.inject.Inject
import javax.script.ScriptEngineManager;

class CalcManagerImpl @Inject constructor() : CalcManager {

    override fun calculate(
        calcOperation: CalcOperation,
        firstNumber: String,
        secondNumber: String
    ): CalcManagerResult {
        val firstNumberToDouble = firstNumber.toDoubleOrNull()
        val secondNumberToDouble = secondNumber.toDoubleOrNull()
        if (firstNumberToDouble != null && secondNumberToDouble != null) {
            return try {
                val result = when (calcOperation) {
                    is CalcOperation.Add -> firstNumberToDouble + secondNumberToDouble
                    is CalcOperation.Sub -> firstNumberToDouble - secondNumberToDouble
                    is CalcOperation.Mul -> firstNumberToDouble * secondNumberToDouble
                    is CalcOperation.Div -> firstNumberToDouble / secondNumberToDouble
                }
                CalcManagerResult(CalcManagerResultMode.number, result.toString())
            } catch (ex: Exception) {
                ex.printStackTrace()
                CalcManagerResult(CalcManagerResultMode.error)
            }
        }
        return CalcManagerResult(CalcManagerResultMode.none)
    }
}