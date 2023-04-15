package com.example.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.state.CalcState
import javax.inject.Inject

class CalcViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(CalcState())

}