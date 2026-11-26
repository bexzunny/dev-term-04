package com.example.gatogo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gatogo.R
import com.example.gatogo.ui.components.ImagenLocal
import com.example.gatogo.ui.viewmodel.DetalleGatoViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleGatoScreen(
    navController: NavHostController,
    idGato: Int,
    idUsuario: Int?,
    viewModel: DetalleGatoViewModel = hiltViewModel()
) {
    val gato by remember(idGato) { viewModel.gato(idGato) }.collectAsState(initial = null)
    val avistamientos by remember(idGato) { viewModel.avistamientos(idGato) }.collectAsState(initial = emptyList())
    var mostrarEliminacion by rememberSaveable { mutableStateOf(false) }
    var mostrarEdicion by rememberSaveable { mutableStateOf(false) }
    var descripcionEditada by rememberSaveable { mutableStateOf("") }
    // Guardamos solo el identificador: un objeto Avistamiento no es Saveable.
    // Así se puede reconstruir la selección desde los datos observados en Room.
    var avistamientoSeleccionadoId by rememberSaveable { mutableIntStateOf(-1) }
    val avistamientoSeleccionado = avistamientos.firstOrNull {
        it.idAvistamiento == avistamientoSeleccionadoId
    }
    val resultado by viewModel.resultado.collectAsState()
    val textoResultado = resultado?.let { stringResource(it) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(resultado) {
        if (resultado != null) {
            snackbarHostState.showSnackbar(textoResultado.orEmpty())
            viewModel.limpiarResultado()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.common_back))
                    }
                }
            )
        }
    ) { padding ->
        if (gato == null) {
            Text(
                stringResource(R.string.detail_no_cat),
                modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(
                                gato!!.nombre.ifBlank { stringResource(R.string.unknown_cat_name) },
                                style = MaterialTheme.typography.titleLarge
                            )
                            if (gato!!.colores.isNotBlank()) Text(stringResource(R.string.detail_color, gato!!.colores))
                            if (gato!!.raza.isNotBlank()) Text(stringResource(R.string.detail_breed, gato!!.raza))
                            if (gato!!.tamano.isNotBlank()) Text(stringResource(R.string.detail_size, gato!!.tamano))
                            if (gato!!.descripcion.isNotBlank()) Text(gato!!.descripcion)
                        }
                    }
                }
                item {
                    Text(
                        stringResource(R.string.detail_sightings),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                if (avistamientos.isEmpty()) {
                    item { Text(stringResource(R.string.detail_no_sightings)) }
                } else {
                    items(avistamientos, key = { it.idAvistamiento }) { avistamiento ->
                        val fotos by remember(avistamiento.idAvistamiento) {
                            viewModel.fotografias(avistamiento.idAvistamiento)
                        }.collectAsState(initial = emptyList())
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(stringResource(R.string.detail_date, avistamiento.fecha))
                                if (avistamiento.latitud != null && avistamiento.longitud != null) {
                                    Text(
                                        stringResource(
                                            R.string.detail_location,
                                            (avistamiento.latitud * 100).roundToInt() / 100.0,
                                            (avistamiento.longitud * 100).roundToInt() / 100.0
                                        )
                                    )
                                }
                                if (avistamiento.descripcion.isNotBlank()) Text(avistamiento.descripcion)
                                fotos.forEach { foto ->
                                    ImagenLocal(foto.urlFotografia, Modifier.fillMaxWidth().height(170.dp))
                                }
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    if (idUsuario == avistamiento.idUsuario) {
                                        TextButton(onClick = {
                                            avistamientoSeleccionadoId = avistamiento.idAvistamiento
                                            descripcionEditada = avistamiento.descripcion
                                            mostrarEdicion = true
                                        }) {
                                            Text(stringResource(R.string.detail_edit))
                                        }
                                        TextButton(onClick = {
                                            avistamientoSeleccionadoId = avistamiento.idAvistamiento
                                            mostrarEliminacion = true
                                        }) {
                                            Text(stringResource(R.string.detail_delete))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    if (mostrarEdicion && avistamientoSeleccionado != null) {
        AlertDialog(
            onDismissRequest = { mostrarEdicion = false },
            title = { Text(stringResource(R.string.detail_edit)) },
            text = {
                OutlinedTextField(
                    value = descripcionEditada,
                    onValueChange = { descripcionEditada = it },
                    label = { Text(stringResource(R.string.register_description)) }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.actualizarDescripcion(
                        avistamientoSeleccionado!!,
                        descripcionEditada
                    )
                    mostrarEdicion = false
                }) { Text(stringResource(R.string.common_save)) }
            },
            dismissButton = {
                TextButton(onClick = { mostrarEdicion = false }) {
                    Text(stringResource(R.string.common_cancel))
                }
            }
        )
    }

    if (mostrarEliminacion && avistamientoSeleccionado != null) {
        AlertDialog(
            onDismissRequest = { mostrarEliminacion = false },
            title = { Text(stringResource(R.string.detail_delete)) },
            text = { Text(stringResource(R.string.detail_delete_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.eliminar(avistamientoSeleccionado!!)
                    mostrarEliminacion = false
                }) { Text(stringResource(R.string.detail_delete)) }
            },
            dismissButton = {
                TextButton(onClick = { mostrarEliminacion = false }) {
                    Text(stringResource(R.string.common_cancel))
                }
            }
        )
    }
}
