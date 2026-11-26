package com.example.gatogo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gatogo.R
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
    var editarNombre by rememberSaveable { mutableStateOf(false) }
    var cambiarContrasena by rememberSaveable { mutableStateOf(false) }
    var confirmarCierre by rememberSaveable { mutableStateOf(false) }
    var confirmarEliminacion by rememberSaveable { mutableStateOf(false) }
    var nombreNuevo by rememberSaveable { mutableStateOf("") }
    var contrasenaNueva by rememberSaveable { mutableStateOf("") }

    val usuarioFlow = remember(idUsuario) {
        if (idUsuario != null) usuarioViewModel.obtenerUsuarioPorId(idUsuario)
        else emptyFlow<Usuario?>()
    }
    val usuario by usuarioFlow.collectAsState(initial = null)

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.profile_title)) }) },
        bottomBar = { BarraNavegacion(navController, AppScreens.PERFIL) }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text(usuario?.nombre ?: stringResource(R.string.profile_account))
                    Text(usuario?.correo ?: stringResource(R.string.profile_loading))
                }
            }
            Button(
                onClick = { nombreNuevo = usuario?.nombre.orEmpty(); editarNombre = true },
                modifier = Modifier.fillMaxWidth(),
                enabled = usuario != null
            ) { Text(stringResource(R.string.profile_edit_name)) }
            Button(
                onClick = { contrasenaNueva = ""; cambiarContrasena = true },
                modifier = Modifier.fillMaxWidth(),
                enabled = usuario != null
            ) { Text(stringResource(R.string.profile_change_password)) }
            TextButton(
                onClick = { confirmarEliminacion = true },
                modifier = Modifier.fillMaxWidth(),
                enabled = idUsuario != null
            ) { Text(stringResource(R.string.profile_delete_account)) }
            TextButton(
                onClick = { confirmarCierre = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text(stringResource(R.string.profile_logout)) }
        }
    }

    if (editarNombre) {
        AlertDialog(
            onDismissRequest = { editarNombre = false },
            title = { Text(stringResource(R.string.profile_edit_name)) },
            text = {
                OutlinedTextField(
                    value = nombreNuevo,
                    onValueChange = { nombreNuevo = it },
                    label = { Text(stringResource(R.string.profile_name_label)) },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (idUsuario != null && nombreNuevo.isNotBlank()) {
                        usuarioViewModel.actualizarNombre(idUsuario, nombreNuevo)
                    }
                    editarNombre = false
                }) { Text(stringResource(R.string.common_save)) }
            },
            dismissButton = {
                TextButton(onClick = { editarNombre = false }) { Text(stringResource(R.string.common_cancel)) }
            }
        )
    }

    if (cambiarContrasena) {
        AlertDialog(
            onDismissRequest = { cambiarContrasena = false },
            title = { Text(stringResource(R.string.profile_change_password)) },
            text = {
                OutlinedTextField(
                    value = contrasenaNueva,
                    onValueChange = { contrasenaNueva = it },
                    label = { Text(stringResource(R.string.profile_new_password)) },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (idUsuario != null && contrasenaNueva.length >= 8) {
                        usuarioViewModel.actualizarContrasena(idUsuario, contrasenaNueva)
                        cambiarContrasena = false
                    }
                }) { Text(stringResource(R.string.common_save)) }
            },
            dismissButton = {
                TextButton(onClick = { cambiarContrasena = false }) { Text(stringResource(R.string.common_cancel)) }
            }
        )
    }

    if (confirmarEliminacion && idUsuario != null) {
        AlertDialog(
            onDismissRequest = { confirmarEliminacion = false },
            title = { Text(stringResource(R.string.profile_delete_account)) },
            text = { Text(stringResource(R.string.profile_delete_account_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    confirmarEliminacion = false
                    usuarioViewModel.eliminarCuentaLocal(idUsuario)
                }) { Text(stringResource(R.string.profile_delete_account)) }
            },
            dismissButton = {
                TextButton(onClick = { confirmarEliminacion = false }) { Text(stringResource(R.string.common_cancel)) }
            }
        )
    }
    if (confirmarCierre) {
        AlertDialog(
            onDismissRequest = { confirmarCierre = false },
            title = { Text(stringResource(R.string.profile_logout)) },
            text = { Text(stringResource(R.string.profile_logout_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    confirmarCierre = false
                    usuarioViewModel.cerrarSesion()
                }) { Text(stringResource(R.string.profile_logout)) }
            },
            dismissButton = {
                TextButton(onClick = { confirmarCierre = false }) { Text(stringResource(R.string.common_cancel)) }
            }
        )
    }
}
