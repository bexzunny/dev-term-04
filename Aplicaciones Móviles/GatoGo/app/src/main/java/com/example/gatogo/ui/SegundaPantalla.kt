package com.example.gatogo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gatogo.R
import com.example.gatogo.data.demo.CoordenadasAleatorias
import com.example.gatogo.domain.model.Avistamiento
import com.example.gatogo.domain.model.Gato
import com.example.gatogo.ui.theme.GatoGoTheme
import com.example.gatogo.ui.viewmodel.GatoViewModel
import com.example.gatogo.ui.viewmodel.MapaViewModel

@Composable
fun SegundaPantalla(
    navController: NavHostController? = null,
    viewModel: GatoViewModel = hiltViewModel(),
    mapaViewModel: MapaViewModel = hiltViewModel()
) {
    val gatos by viewModel.gatos.collectAsState()
    val avistamientos by mapaViewModel.avistamientos.collectAsState()
    var filtroSeleccionado by rememberSaveable { mutableStateOf("todos") }

    Scaffold(
        topBar = { TopBarMapa(onSeleccionarFiltro = { filtroSeleccionado = it }) },
        bottomBar = { BarraNavegacion(navController, AppScreens.MAPA) }
    ) { padding ->
        ContenidoMapa(
            modifier = Modifier.padding(padding),
            gatos = gatos,
            avistamientos = avistamientos,
            filtroSeleccionado = filtroSeleccionado,
            onFiltroSeleccionado = { filtroSeleccionado = it },
            onRegistrar = { navController?.navigate(AppScreens.REGISTRAR) },
            onDetalle = { idGato -> navController?.navigate(AppScreens.detalleGato(idGato)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMapa(onSeleccionarFiltro: (String) -> Unit = {}) {
    var menuAbierto by rememberSaveable { mutableStateOf(false) }
    TopAppBar(
        title = { Text(stringResource(R.string.map_title), fontWeight = FontWeight.Bold) },
        actions = {
            Box {
                IconButton(onClick = { menuAbierto = true }) {
                    Icon(Icons.Outlined.Tune, contentDescription = stringResource(R.string.map_filters))
                }
                DropdownMenu(expanded = menuAbierto, onDismissRequest = { menuAbierto = false }) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.map_filter_all)) },
                        onClick = { onSeleccionarFiltro("todos"); menuAbierto = false }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.map_filter_orange)) },
                        onClick = { onSeleccionarFiltro("naranja"); menuAbierto = false }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.map_filter_no_collar)) },
                        onClick = { onSeleccionarFiltro("sin_collar"); menuAbierto = false }
                    )
                }
            }
        }
    )
}

@Composable
fun ContenidoMapa(
    modifier: Modifier = Modifier,
    gatos: List<Gato> = emptyList(),
    avistamientos: List<Avistamiento> = emptyList(),
    filtroSeleccionado: String = "todos",
    onFiltroSeleccionado: (String) -> Unit = {},
    onRegistrar: () -> Unit = {},
    onDetalle: (Int) -> Unit = {}
) {
    var busqueda by rememberSaveable { mutableStateOf("") }
    var avistamientoSeleccionado by rememberSaveable { mutableIntStateOf(-1) }

    val gatosFiltrados = gatos.filter { gato ->
        val texto = "${gato.nombre} ${gato.colores} ${gato.descripcion} ${gato.raza} ${gato.tamano}"
        val coincideBusqueda = texto.contains(busqueda.trim(), ignoreCase = true)
        val coincideFiltro = when (filtroSeleccionado) {
            "naranja" -> gato.colores.contains("naranja", ignoreCase = true)
            "sin_collar" -> gato.descripcion.contains("sin collar", ignoreCase = true)
            else -> true
        }
        coincideBusqueda && coincideFiltro
    }
    val idsVisibles = gatosFiltrados.map { it.idGato }.toSet()
    val avistamientosVisibles = avistamientos.filter {
        it.idGato in idsVisibles && it.latitud != null && it.longitud != null
    }
    val seleccionado = avistamientosVisibles.firstOrNull {
        it.idAvistamiento == avistamientoSeleccionado
    }
    val gatoSeleccionado = seleccionado?.let { sighting ->
        gatos.firstOrNull { it.idGato == sighting.idGato }
    }

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
    ) {
        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(R.string.map_search)) },
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = stringResource(R.string.map_search_desc)) },
            singleLine = true
        )
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = filtroSeleccionado == "todos",
                onClick = { onFiltroSeleccionado("todos") },
                label = { Text(stringResource(R.string.map_filter_all)) }
            )
            FilterChip(
                selected = filtroSeleccionado == "naranja",
                onClick = { onFiltroSeleccionado("naranja") },
                label = { Text(stringResource(R.string.map_filter_orange)) }
            )
            FilterChip(
                selected = filtroSeleccionado == "sin_collar",
                onClick = { onFiltroSeleccionado("sin_collar") },
                label = { Text(stringResource(R.string.map_filter_no_collar)) }
            )
        }

        MapaOffline(
            avistamientos = avistamientosVisibles,
            gatos = gatos,
            seleccionadoId = avistamientoSeleccionado,
            onSeleccionar = { avistamientoSeleccionado = it },
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            stringResource(R.string.map_demo_zone),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp)
        )

        if (seleccionado != null && gatoSeleccionado != null) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        gatoSeleccionado.nombre.ifBlank { stringResource(R.string.unknown_cat_name) },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(gatoSeleccionado.colores)
                    Text(seleccionado.descripcion)
                    Button(
                        onClick = { onDetalle(gatoSeleccionado.idGato) },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(stringResource(R.string.map_open_detail)) }
                }
            }
        }

        Text(
            stringResource(R.string.map_registered),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
        )
        if (gatosFiltrados.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text(
                    stringResource(if (gatos.isEmpty()) R.string.map_empty else R.string.map_no_matches),
                    modifier = Modifier.padding(18.dp)
                )
            }
        } else {
            gatosFiltrados.forEach { gato ->
                TarjetaGato(
                    gato = gato,
                    modifier = Modifier.padding(bottom = 10.dp),
                    onClick = { onDetalle(gato.idGato) }
                )
            }
        }

        Button(
            onClick = onRegistrar,
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
        ) {
            Icon(Icons.Outlined.CameraAlt, contentDescription = stringResource(R.string.map_capture))
            Text(stringResource(R.string.map_capture), modifier = Modifier.padding(start = 8.dp), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun MapaOffline(
    avistamientos: List<Avistamiento>,
    gatos: List<Gato>,
    seleccionadoId: Int,
    onSeleccionar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().height(290.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        // Calles dibujadas localmente: no hay mosaicos, red ni SDK cartográfico.
        Box(Modifier.fillMaxWidth().height(18.dp).align(Alignment.Center).background(MaterialTheme.colorScheme.surface))
        Box(Modifier.fillMaxHeight().fillMaxWidth(0.10f).align(Alignment.Center).background(MaterialTheme.colorScheme.surface))
        Box(Modifier.fillMaxWidth(0.82f).height(9.dp).align(Alignment.TopCenter).offset(y = 54.dp).background(MaterialTheme.colorScheme.surface))
        Box(Modifier.fillMaxWidth(0.78f).height(9.dp).align(Alignment.BottomCenter).offset(y = (-62).dp).background(MaterialTheme.colorScheme.surface))
        Box(
            Modifier.size(72.dp).align(Alignment.TopStart).padding(12.dp)
                .clip(CircleShape).background(MaterialTheme.colorScheme.tertiaryContainer)
        )
        Box(
            Modifier.size(92.dp).align(Alignment.BottomEnd).padding(12.dp)
                .clip(CircleShape).background(MaterialTheme.colorScheme.tertiaryContainer)
        )
        Text(
            stringResource(R.string.map_offline_label),
            modifier = Modifier.align(Alignment.TopStart).padding(start = 18.dp, top = 88.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        avistamientos.forEach { sighting ->
            val lat = sighting.latitud ?: return@forEach
            val lon = sighting.longitud ?: return@forEach
            val x = CoordenadasAleatorias.posicionX(lon)
            val y = CoordenadasAleatorias.posicionY(lat)
            val gato = gatos.firstOrNull { it.idGato == sighting.idGato }
            IconButton(
                onClick = { onSeleccionar(sighting.idAvistamiento) },
                modifier = Modifier.offset(
                    x = maxWidth * x - 20.dp,
                    y = maxHeight * y - 20.dp
                ).size(40.dp).background(
                    if (sighting.idAvistamiento == seleccionadoId) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface,
                    CircleShape
                )
            ) {
                Icon(
                    Icons.Outlined.Pets,
                    contentDescription = gato?.nombre ?: stringResource(R.string.unknown_cat_name),
                    tint = if (sighting.idAvistamiento == seleccionadoId) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun TarjetaGato(gato: Gato, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Pets,
                contentDescription = gato.nombre.ifBlank { stringResource(R.string.map_cats_desc) },
                modifier = Modifier.size(65.dp).background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(10.dp)
                ).padding(16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(gato.nombre.ifBlank { stringResource(R.string.unknown_cat_name) }, fontWeight = FontWeight.Bold)
                if (gato.colores.isNotBlank()) Text(gato.colores, style = MaterialTheme.typography.bodySmall)
                if (gato.raza.isNotBlank()) Text(gato.raza, style = MaterialTheme.typography.bodySmall)
                if (gato.tamano.isNotBlank()) Text(gato.tamano, style = MaterialTheme.typography.bodySmall)
                if (gato.descripcion.isNotBlank()) Text(
                    gato.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun segundaPantallaPreview() {
    GatoGoTheme { ContenidoMapa(gatos = emptyList()) }
}
