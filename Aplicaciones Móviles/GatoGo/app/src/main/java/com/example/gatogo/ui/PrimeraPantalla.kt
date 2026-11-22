package com.example.gatogo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gatogo.R
import com.example.gatogo.ui.theme.GatoGoTheme

@Composable
fun PrimeraPantalla(navController: NavHostController? = null) {
    Scaffold(
        topBar = { TopBarPrimeraPantalla(onPerfil = { navController?.navigate(AppScreens.PERFIL) }) },
        bottomBar = { BarraNavegacion(navController, AppScreens.INICIO) }
    ) { innerPadding ->
        bodyPrimeraPantalla(
            modifier = Modifier.padding(innerPadding),
            onEntrar = { navController?.navigate(AppScreens.MAPA) },
            onRegistrar = { navController?.navigate(AppScreens.REGISTRAR) },
            onColeccion = { navController?.navigate(AppScreens.COLECCION) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarPrimeraPantalla(onPerfil: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displaySmall
            )
        },
        actions = {
            IconButton(onClick = onPerfil) {
                Icon(
                    imageVector = Icons.Rounded.Pets,
                    contentDescription = stringResource(R.string.descripcion_mascotas)
                )
            }
        }
    )
}

@Composable
fun bodyPrimeraPantalla(
    modifier: Modifier,
    onEntrar: () -> Unit = {},
    onRegistrar: () -> Unit = {},
    onColeccion: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.descripcion_logo),
            modifier = Modifier.size(70.dp)
        )
        Text(
            text = stringResource(R.string.titulo_inicio),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.descripcion_inicio),
            modifier = Modifier.width(250.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center
        )
        Button(onClick = onEntrar, modifier = Modifier.width(250.dp)) {
            Text(stringResource(R.string.boton_entrar))
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = stringResource(R.string.boton_entrar)
            )
        }
        TarjetasInicio(onRegistrar = onRegistrar, onColeccion = onColeccion)
        AvisoRespeto()
    }
}

@Composable
fun TarjetasInicio(
    onRegistrar: () -> Unit = {},
    onColeccion: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            onClick = onRegistrar,
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Icon(
                    imageVector = Icons.Outlined.CameraAlt,
                    contentDescription = stringResource(R.string.registrar_gato),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.registrar_gato),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = stringResource(R.string.guardar_encuentro),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 7.dp)
                )
            }
        }
        Card(onClick = onColeccion, modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Icon(
                    imageVector = Icons.Outlined.GridView,
                    contentDescription = stringResource(R.string.mi_coleccion),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.mi_coleccion),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = stringResource(R.string.tus_registros),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 7.dp)
                )
            }
        }
    }
}

@Composable
fun AvisoRespeto() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.aviso_respeto_titulo),
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Shield,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.aviso_privacidad),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun primeraPantallaPreview() {
    GatoGoTheme { PrimeraPantalla() }
}
