package com.example.gatogo.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gatogo.R
import com.example.gatogo.ui.components.ImagenLocal
import com.example.gatogo.ui.viewmodel.GatoViewModel
import com.example.gatogo.ui.viewmodel.RegistroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarGatoScreen(
    navController: NavHostController,
    idUsuario: Int?,
    viewModel: RegistroViewModel = hiltViewModel(),
    gatoViewModel: GatoViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val errorFotoTexto = stringResource(R.string.register_select_photo_error)
    val registroGuardadoTexto = stringResource(R.string.register_saved)
    val gatos by gatoViewModel.gatos.collectAsState()
    var menuGatosAbierto by rememberSaveable { mutableStateOf(false) }
    var idGatoExistente by rememberSaveable { mutableStateOf<Int?>(null) }
    var nombre by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }
    var raza by rememberSaveable { mutableStateOf("") }
    var tamano by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var fotografiaUri by rememberSaveable { mutableStateOf<String?>(null) }
    var mensajeFoto by rememberSaveable { mutableStateOf("") }
    val estado by viewModel.estado.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val selectorFoto = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }.onSuccess {
                fotografiaUri = uri.toString()
                mensajeFoto = ""
            }.onFailure {
                mensajeFoto = errorFotoTexto
            }
        }
    }

    LaunchedEffect(estado.idGato) {
        if (estado.idGato != null) {
            snackbarHostState.showSnackbar(registroGuardadoTexto)
            viewModel.limpiarEstado()
            navController.navigate(AppScreens.MAPA) {
                popUpTo(AppScreens.INICIO) { inclusive = false }
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.register_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.common_back)
                        )
                    }
                }
            )
        },
        bottomBar = { BarraNavegacion(navController, AppScreens.REGISTRAR) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .imePadding().padding(padding).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                stringResource(R.string.register_intro),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box {
                OutlinedButton(
                    onClick = { menuGatosAbierto = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (idGatoExistente == null) {
                            stringResource(R.string.register_new_cat)
                        } else {
                            gatos.firstOrNull { it.idGato == idGatoExistente }?.nombre
                                ?.ifBlank { context.getString(R.string.unknown_cat_name) }
                                ?: stringResource(R.string.register_existing_cat)
                        }
                    )
                }
                DropdownMenu(
                    expanded = menuGatosAbierto,
                    onDismissRequest = { menuGatosAbierto = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.register_new_cat)) },
                        onClick = {
                            idGatoExistente = null
                            menuGatosAbierto = false
                        }
                    )
                    gatos.forEach { gato ->
                        DropdownMenuItem(
                            text = {
                                Text(gato.nombre.ifBlank { context.getString(R.string.unknown_cat_name) })
                            },
                            onClick = {
                                idGatoExistente = gato.idGato
                                menuGatosAbierto = false
                            }
                        )
                    }
                }
            }

            if (idGatoExistente == null) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.register_name)) },
                    singleLine = true
                )
                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.register_color)) },
                    placeholder = { Text(stringResource(R.string.register_color_hint)) },
                    singleLine = true
                )
                OutlinedTextField(
                    value = raza,
                    onValueChange = { raza = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.register_breed)) },
                    singleLine = true
                )
                OutlinedTextField(
                    value = tamano,
                    onValueChange = { tamano = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.register_size)) },
                    singleLine = true
                )
            }

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.register_description)) },
                minLines = 3,
                maxLines = 5
            )

            OutlinedButton(
                onClick = { selectorFoto.launch(arrayOf("image/*")) },
                modifier = Modifier.fillMaxWidth()
            ) { Text(stringResource(R.string.register_photo)) }

            if (fotografiaUri != null) {
                ImagenLocal(fotografiaUri, Modifier.fillMaxWidth().height(180.dp))
            }
            if (mensajeFoto.isNotBlank()) {
                Text(mensajeFoto, color = MaterialTheme.colorScheme.error)
            }

            Text(
                stringResource(R.string.register_location_auto),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (estado.errorRes != null) {
                Text(
                    stringResource(estado.errorRes!!),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = {
                    if (idUsuario != null) {
                        viewModel.registrar(
                            idUsuario = idUsuario,
                            idGatoExistente = idGatoExistente,
                            nombre = nombre,
                            color = color,
                            raza = raza,
                            tamano = tamano,
                            descripcion = descripcion,
                            latitud = null,
                            longitud = null,
                            fotografiaUri = fotografiaUri
                        )
                    }
                },
                enabled = !estado.guardando && idUsuario != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (estado.guardando) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(end = 10.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                }
                Text(stringResource(R.string.register_save))
            }
            Text(
                stringResource(R.string.register_required_note),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
