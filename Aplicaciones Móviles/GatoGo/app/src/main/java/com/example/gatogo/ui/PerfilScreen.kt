package com.example.gatogo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gatogo.domain.model.Usuario
import com.example.gatogo.ui.viewmodel.UsuarioViewModel
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavHostController,
    idUsuario: Int?,
    usuarioViewModel: UsuarioViewModel
) {
    var confirmarCierre by rememberSaveable { mutableStateOf(false) }
    val usuarioFlow = remember(idUsuario) {
        if (idUsuario != null) usuarioViewModel.obtenerUsuarioPorId(idUsuario)
        else emptyFlow<Usuario?>()
    }
    val usuario by usuarioFlow.collectAsState(initial = null)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Perfil", fontWeight = FontWeight.Bold) }) },
        bottomBar = { BarraNavegacion(navController, AppScreens.PERFIL) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        usuario?.nombre ?: "Cuenta GatoGO",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        usuario?.correo ?: "Cargando información de la cuenta…",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
            Text(
                "Tu sesión se conserva en este dispositivo. La aplicación almacena el identificador del usuario, no la contraseña.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            OutlinedButton(
                onClick = { navController.navigate(AppScreens.COLECCION) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Consultar mi colección")
            }
            Button(
                onClick = { confirmarCierre = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }

    if (confirmarCierre) {
        AlertDialog(
            onDismissRequest = { confirmarCierre = false },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Quieres cerrar tu sesión en este dispositivo?") },
            confirmButton = {
                TextButton(onClick = {
                    confirmarCierre = false
                    usuarioViewModel.cerrarSesion()
                }) { Text("Cerrar sesión") }
            },
            dismissButton = {
                TextButton(onClick = { confirmarCierre = false }) { Text("Cancelar") }
            }
        )
    }
}
