package com.example.date_pickers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.date_pickers.ui.theme.Date_pickersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Date_pickersTheme {
                EjemploDatePickers()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemploDatePickers() {

    var tipo by remember { mutableIntStateOf(0) }
    var mostrarModal by remember { mutableStateOf(false) }
    var mostrarInput by remember { mutableStateOf(false) }

    val dockedState = rememberDatePickerState()
    val modalState = rememberDatePickerState()
    val inputState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input
    )

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Date Pickers",
                style = MaterialTheme.typography.headlineSmall
            )

            Text("Selecciona un tipo de selector:")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { tipo = 0 }) {
                    Text("Docked")
                }

                Button(onClick = { mostrarModal = true }) {
                    Text("Modal")
                }

                Button(onClick = { mostrarInput = true }) {
                    Text("Input")
                }
            }

            when (tipo) {
                0 -> {
                    Text(
                        text = "Docked Date Picker",
                        style = MaterialTheme.typography.titleMedium
                    )

                    DatePicker(state = dockedState)
                }
            }
        }
    }

    // Modal Date Picker: calendario en un diálogo
    if (mostrarModal) {
        DatePickerDialog(
            onDismissRequest = { mostrarModal = false },
            confirmButton = {
                TextButton(
                    onClick = { mostrarModal = false }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { mostrarModal = false }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = modalState)
        }
    }

    // Modal Date Input: entrada de fecha mediante campos
    if (mostrarInput) {
        DatePickerDialog(
            onDismissRequest = { mostrarInput = false },
            confirmButton = {
                TextButton(
                    onClick = { mostrarInput = false }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { mostrarInput = false }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = inputState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Date_pickersTheme {
    }
}