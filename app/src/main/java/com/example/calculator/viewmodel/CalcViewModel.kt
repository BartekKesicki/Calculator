package com.example.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.actions.CalcAction
import com.example.calculator.actions.CalcOperation
import com.example.calculator.calcmanager.CalcManager
import com.example.calculator.calcmanager.CalcManagerResult
import com.example.calculator.calcmanager.CalcManagerResultMode
import com.example.calculator.common.AppConstants
import com.example.calculator.state.CalcState
import javax.inject.Inject

class CalcViewModel @Inject constructor(private val calcManager: CalcManager) : ViewModel() {

    var state by mutableStateOf(CalcState())

    fun onAction(action: CalcAction) {
        when (action) {
            is CalcAction.Number -> putNumber(action.value)
            is CalcAction.Operation -> putOperation(action.operation)
            is CalcAction.PutDecimal -> putDecimal()
            is CalcAction.Delete -> delete()
            is CalcAction.Clear -> clear()
            is CalcAction.Calculate -> calculate()
        }
    }

    private fun putNumber(number: Int) {
        val maximumNumberLength = 8
        val isErrorDisplayed =
            state.calcOperation == null && state.firstNumber == AppConstants.ERROR
        val isFirstNumberWriting =
            state.calcOperation == null && state.firstNumber.length < maximumNumberLength
        val isSecondNumberWriting =
            state.calcOperation != null && state.secondNumber.length < maximumNumberLength
        if (isErrorDisplayed) {
            clear()
        }
        if (isFirstNumberWriting) {
            state = state.copy(firstNumber = state.firstNumber + number)
            return
        }
        if (isSecondNumberWriting) {
            state = state.copy(
                secondNumber = state.secondNumber + number
            )
        }
    }

    private fun putOperation(operation: CalcOperation) {
        if (state.firstNumber.isNotBlank() && state.firstNumber == AppConstants.ERROR) {
            clear()
        }
        if (state.firstNumber.isNotBlank()) {
            state = state.copy(calcOperation = operation)
        }
    }

    private fun putDecimal() {
        if (state.calcOperation == null && state.firstNumber == AppConstants.ERROR) {
            clear()
            return
        }
        if (state.calcOperation == null && !state.firstNumber.contains(".") && state.firstNumber.isNotBlank()) {
            state = state.copy(firstNumber = state.firstNumber + ".")
            return
        }
        if (!state.secondNumber.contains(".") && state.secondNumber.isNotBlank()) {
            state = state.copy(firstNumber = state.secondNumber + ".")
            return
        }
    }

    private fun delete() {
        when {
            state.secondNumber.isNotBlank() -> state =
                state.copy(secondNumber = state.secondNumber.dropLast(1))

            state.firstNumber.isNotBlank() && state.firstNumber == AppConstants.ERROR ->
                state = CalcState()

            state.firstNumber.isNotBlank() && state.calcOperation != null -> state =
                state.copy(firstNumber = state.firstNumber, calcOperation = null)

            state.firstNumber.isNotBlank() -> state =
                state.copy(firstNumber = state.firstNumber.dropLast(1))
        }
    }

    private fun clear() {
        state = CalcState()
    }

    private fun calculate() {
        var result: CalcManagerResult? = null
        if (state.calcOperation != null) {
            result = calcManager.calculate(
                state.calcOperation!!,
                firstNumber = state.firstNumber,
                secondNumber = state.secondNumber
            )
        }
        if (result != null && result.mode == CalcManagerResultMode.error) {
            state = state.copy(firstNumber = AppConstants.ERROR)
        } else if (result != null && result.mode == CalcManagerResultMode.number) {
            state = state.copy(firstNumber = result.value, calcOperation = null, secondNumber = "")
        }
    }
}