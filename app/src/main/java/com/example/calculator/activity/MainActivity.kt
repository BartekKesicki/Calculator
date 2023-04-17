package com.example.calculator.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.calculator.app.App
import com.example.calculator.calcmanager.CalcManager
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.viewmodel.CalcViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       (applicationContext as App).appComponent.inject(this)
        setContent {
            CalculatorTheme {
                val viewModel = ViewModelProvider(this, viewModelFactory)[CalcViewModel::class.java]
                val state = viewModel.state

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text("Hello")
                }
            }
        }
    }
}
