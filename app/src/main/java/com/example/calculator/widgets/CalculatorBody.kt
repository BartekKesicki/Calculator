package com.example.calculator.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.calculator.actions.CalcAction
import com.example.calculator.actions.CalcOperation
import com.example.calculator.state.CalcState
import com.example.calculator.ui.theme.ButtonSpacing
import com.example.calculator.ui.theme.DisplayFontSize
import com.example.calculator.ui.theme.DisplayPadding
import com.example.calculator.ui.theme.Gray
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange

@Composable
fun CalculatorBody(
    state: CalcState,
    modifier: Modifier = Modifier,
    onAction: (CalcAction) -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(ButtonSpacing)
        ) {
            Display(state)
            Keyboard(
                modifier = modifier,
                onAction = onAction
            )
        }
    }
}

@Composable
fun Display(state: CalcState) {
    Text(
        text = state.firstNumber + (state.calcOperation ?: "") + state.secondNumber,
        textAlign = TextAlign.End,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(DisplayPadding),
        fontWeight = FontWeight.Light,
        fontSize = DisplayFontSize,
        maxLines = 2
    )
}

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    onAction: (CalcAction) -> Unit
) {
    val numberButtonModifier = Modifier
        .background(
            Gray
        )
        .aspectRatio(1f)
    val operationButtonModifier = Modifier
        .background(
            Orange
        )
        .aspectRatio(1f)
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ButtonSpacing)
        ) {
            CalcActionButton(
                text = "AC",
                onClick = { onAction(CalcAction.Clear) },
                modifier = Modifier
                    .background(
                        LightGray
                    )
                    .aspectRatio(2f)
                    .weight(2f)
            )
            CalcActionButton(
                text = "Del",
                onClick = { onAction(CalcAction.Delete) },
                modifier = Modifier
                    .background(
                        Gray
                    )
                    .aspectRatio(1f)
                    .weight(1f)
            )
            OperationButton(
                operation = "/",
                onClick = { onAction(CalcAction.Operation(CalcOperation.Div)) },
                modifier = operationButtonModifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ButtonSpacing)
        ) {
            NumberButton(
                number = 7, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            NumberButton(
                number = 8, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            NumberButton(
                number = 9, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            OperationButton(
                operation = "x",
                onClick = { onAction(CalcAction.Operation(CalcOperation.Mul)) },
                modifier = operationButtonModifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ButtonSpacing)
        ) {
            NumberButton(
                number = 4, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            NumberButton(
                number = 5, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            NumberButton(
                number = 6, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            OperationButton(
                operation = "-",
                onClick = { onAction(CalcAction.Operation(CalcOperation.Sub)) },
                modifier = operationButtonModifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ButtonSpacing)
        ) {
            NumberButton(
                number = 1, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            NumberButton(
                number = 2, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            NumberButton(
                number = 3, modifier = numberButtonModifier
                    .weight(1f),
                onAction = onAction
            )
            OperationButton(
                operation = "+",
                onClick = { onAction(CalcAction.Operation(CalcOperation.Add)) },
                modifier = operationButtonModifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ButtonSpacing)
        ) {
            NumberButton(
                number = 0, modifier = Modifier
                    .background(Gray)
                    .aspectRatio(2f)
                    .weight(2f),
                onAction = onAction
            )
            DecimalButton( modifier = numberButtonModifier
                    .weight(1f),
                onClick = { onAction(CalcAction.PutDecimal) }
            )
            OperationButton(
                operation = "=",
                onClick = { onAction(CalcAction.Calculate) },
                modifier = operationButtonModifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun NumberButton(number: Int, modifier: Modifier, onAction: (CalcAction) -> Unit) {
    CalcButton(
        text = number.toString(),
        onClick = { onAction(CalcAction.Number(number)) },
        modifier = modifier
    )
}

@Composable
fun CalcActionButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    CalcButton(
        text = text,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun OperationButton(operation: String, modifier: Modifier, onClick: () -> Unit) {
    CalcButton(
        text = operation,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun DecimalButton(modifier: Modifier, onClick: () -> Unit) {
    CalcButton(
        text = ".",
        onClick = onClick,
        modifier = modifier
    )
}