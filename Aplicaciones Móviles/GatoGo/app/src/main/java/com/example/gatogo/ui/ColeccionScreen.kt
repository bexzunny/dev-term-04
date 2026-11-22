package com.example.gatogo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gatogo.domain.model.Gato
import com.example.gatogo.ui.viewmodel.GatoViewModel
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColeccionScreen(
    navController: NavHostController,
    idUsuario: Int?,
    viewModel: GatoViewModel = hiltViewModel()
) {
    val gatosFlow = remember(idUsuario) {
        if (idUsuario != null) viewModel.obtenerGatosPorUsuario(idUsuario)
        else emptyFlow<List<Gato>>()
    }
    val gatos by gatosFlow.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mi colección", fontWeight = FontWeight.Bold) }) },
        bottomBar = { BarraNavegacion(navController, AppScreens.COLECCION) }
    ) { padding ->
        if (gatos.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Todavía no tienes gatos en tu colección.", style = MaterialTheme.typography.titleMedium)
                Text(
                    "La colección muestra los gatos vinculados a tus avistamientos. Registrar un gato y registrar un avistamiento son operaciones distintas.",
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(gatos, key = { it.idGato }) { gato ->
                    TarjetaColeccion(gato)
                }
            }
        }
    }
}

@Composable
private fun TarjetaColeccion(gato: Gato) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(gato.nombre, fontWeight = FontWeight.Bold)
            if (gato.colores.isNotBlank()) Text(gato.colores)
            if (gato.descripcion.isNotBlank()) {
                Text(
                    gato.descripcion,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
