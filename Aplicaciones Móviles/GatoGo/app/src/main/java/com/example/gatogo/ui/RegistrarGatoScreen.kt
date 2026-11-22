package com.example.gatogo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gatogo.ui.viewmodel.GatoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarGatoScreen(
    navController: NavHostController,
    viewModel: GatoViewModel = hiltViewModel()
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var colores by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    val registro by viewModel.registroEstado.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(registro.idCreado) {
        if (registro.idCreado != null) {
            snackbarHostState.showSnackbar("Gato registrado correctamente. ID: ${registro.idCreado}")
            viewModel.limpiarEstadoRegistro()
            navController.navigate(AppScreens.MAPA) {
                popUpTo(AppScreens.INICIO) { inclusive = false }
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar gato", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
        bottomBar = { BarraNavegacion(navController, AppScreens.REGISTRAR) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(padding)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                "Agrega la información básica para identificar al gato. Este registro no crea por sí solo un avistamiento.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre o apodo *") },
                singleLine = true
            )
            OutlinedTextField(
                value = colores,
                onValueChange = { colores = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Color o colores") },
                singleLine = true,
                placeholder = { Text("Ej. naranja, blanco y negro") }
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descripción general") },
                minLines = 4,
                maxLines = 6
            )
            if (registro.error != null) {
                Text(
                    registro.error.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Button(
                onClick = { viewModel.agregarGato(nombre, descripcion, colores) },
                enabled = !registro.guardando,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (registro.guardando) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(end = 10.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                }
                Text("Guardar gato")
            }
            Text(
                "* Campo obligatorio",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
