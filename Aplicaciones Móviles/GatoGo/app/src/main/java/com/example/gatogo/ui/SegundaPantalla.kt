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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Shield
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gatogo.R
import com.example.gatogo.domain.model.Gato
import com.example.gatogo.ui.theme.GatoGoTheme
import com.example.gatogo.ui.viewmodel.GatoViewModel
import kotlinx.coroutines.launch

@Composable
fun SegundaPantalla(
    navController: NavHostController? = null,
    viewModel: GatoViewModel = hiltViewModel()
) {
    val gatos by viewModel.gatos.collectAsState()
    var filtroSeleccionado by rememberSaveable { mutableStateOf("todos") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopBarMapa(onSeleccionarFiltro = { filtroSeleccionado = it }) },
        bottomBar = { BarraNavegacion(navController, AppScreens.MAPA) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        ContenidoMapa(
            modifier = Modifier.padding(innerPadding),
            gatos = gatos,
            filtroSeleccionado = filtroSeleccionado,
            onFiltroSeleccionado = { filtroSeleccionado = it },
            onRegistrar = { navController?.navigate(AppScreens.REGISTRAR) },
            onSolicitarUbicacion = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        "La ubicación se habilitará al integrar el servicio de geolocalización."
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMapa(onSeleccionarFiltro: (String) -> Unit = {}) {
    var menuAbierto by rememberSaveable { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.titulo_mapa),
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Box {
                IconButton(onClick = { menuAbierto = true }) {
                    Icon(
                        imageVector = Icons.Outlined.Tune,
                        contentDescription = stringResource(R.string.descripcion_filtros)
                    )
                }
                DropdownMenu(
                    expanded = menuAbierto,
                    onDismissRequest = { menuAbierto = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Todos") },
                        onClick = { onSeleccionarFiltro("todos"); menuAbierto = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Naranja") },
                        onClick = { onSeleccionarFiltro("naranja"); menuAbierto = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Sin collar") },
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
    filtroSeleccionado: String = "todos",
    onFiltroSeleccionado: (String) -> Unit = {},
    onRegistrar: () -> Unit = {},
    onSolicitarUbicacion: () -> Unit = {}
) {
    var busqueda by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val gatosFiltrados = gatos.filter { gato ->
        val texto = "${gato.nombre} ${gato.colores} ${gato.descripcion}"
        val coincideBusqueda = texto.contains(busqueda.trim(), ignoreCase = true)
        val coincideFiltro = when (filtroSeleccionado) {
            "naranja" -> gato.colores.contains("naranja", ignoreCase = true)

            "sin_collar" -> gato.descripcion.contains("sin collar", ignoreCase = true)
            else -> true
        }
        coincideBusqueda && coincideFiltro
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.buscar_zona_gato)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = stringResource(R.string.descripcion_buscar)
                    )
                },
                singleLine = true
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = filtroSeleccionado == "todos",
                    onClick = { onFiltroSeleccionado("todos") },
                    label = { Text(stringResource(R.string.filtro_todos)) }
                )
                FilterChip(
                    selected = filtroSeleccionado == "naranja",
                    onClick = { onFiltroSeleccionado("naranja") },
                    label = { Text(stringResource(R.string.filtro_naranja)) }
                )
                FilterChip(
                    selected = filtroSeleccionado == "sin_collar",
                    onClick = { onFiltroSeleccionado("sin_collar") },
                    label = { Text(stringResource(R.string.filtro_sin_collar)) }
                )
            }

            MapaIlustrativo(
                modifier = Modifier.padding(top = 16.dp),
                onMiUbicacion = onSolicitarUbicacion
            )

            Text(
                text = "Gatos registrados",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
            )

            if (gatosFiltrados.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            if (gatos.isEmpty()) "Todavía no hay gatos registrados."
                            else "No hay gatos que coincidan con la búsqueda o el filtro.",
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "Los gatos que registres aparecerán aquí automáticamente.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                gatosFiltrados.forEach { gato ->
                    TarjetaGato(
                        gato = gato,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }

            Button(
                onClick = onRegistrar,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.CameraAlt,
                    contentDescription = stringResource(R.string.aviso_capturar)
                )
                Text(
                    text = stringResource(R.string.boton_capturar),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Shield,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.aviso_privacidad_mapa),
                    modifier = Modifier.padding(start = 6.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (scrollState.maxValue > 0) {
            BoxWithConstraints(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(4.dp)
                    .padding(vertical = 4.dp)
            ) {
                val alturaBarra = maxHeight
                val total = (scrollState.viewportSize + scrollState.maxValue).coerceAtLeast(1)
                val proporcion = scrollState.viewportSize.toFloat() / total
                val alturaIndicador = alturaBarra * proporcion
                val desplazamiento = (alturaBarra - alturaIndicador) *
                    (scrollState.value.toFloat() / scrollState.maxValue.coerceAtLeast(1))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(alturaIndicador)
                        .offset(y = desplazamiento)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            }
        }
    }
}


@Composable
fun MapaIlustrativo(
    modifier: Modifier = Modifier,
    onMiUbicacion: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.surface)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(18.dp)
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.surface)
        )
        Text(
            text = stringResource(R.string.calle_paseo_tilos),
            modifier = Modifier.align(Alignment.TopStart).padding(12.dp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.parque_olivo),
            modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        MarcadorGato(
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 55.dp)
        )
        MarcadorGato(
            modifier = Modifier.align(Alignment.TopEnd).padding(top = 65.dp, end = 65.dp)
        )
        MarcadorGato(
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 100.dp, bottom = 50.dp)
        )
        MarcadorGato(
            seleccionado = true,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = onMiUbicacion,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                imageVector = Icons.Outlined.MyLocation,
                contentDescription = stringResource(R.string.mi_ubicacion)
            )
        }
        Text(
            text = "Vista ilustrativa. Las ubicaciones reales se mostrarán al registrar avistamientos.",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(8.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun MarcadorGato(
    seleccionado: Boolean = false,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Outlined.Pets,
        contentDescription = stringResource(R.string.descripcion_gato),
        modifier = modifier
            .size(40.dp)
            .background(
                color = if (seleccionado) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )
            .padding(9.dp),
        tint = if (seleccionado) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.primary
    )
}

@Composable
fun TarjetaGato(gato: Gato, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Pets,
                    contentDescription = gato.nombre,
                    modifier = Modifier
                        .size(65.dp)
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Column(
                    modifier = Modifier.weight(1f).padding(start = 12.dp)
                ) {
                    Text(gato.nombre, fontWeight = FontWeight.Bold)
                    if (gato.colores.isNotBlank()) {
                        Text(gato.colores, style = MaterialTheme.typography.bodySmall)
                    }
                    if (gato.descripcion.isNotBlank()) {
                        Text(
                            gato.descripcion,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        "Registrado el: ${gato.fechaRegistro}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun segundaPantallaPreview() {
    GatoGoTheme {
        ContenidoMapa(gatos = emptyList())
    }
}
