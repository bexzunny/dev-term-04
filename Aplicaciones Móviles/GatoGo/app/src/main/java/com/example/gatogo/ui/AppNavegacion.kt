
package com.example.gatogo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gatogo.ui.viewmodel.UsuarioViewModel

@Composable
fun AppNavegacion(
    usuarioViewModel: UsuarioViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val sesion by usuarioViewModel.sesion.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    // Control de autenticación y navegación
    LaunchedEffect(
        sesion.cargada,
        sesion.idUsuario,
        rutaActual
    ) {
        if (!sesion.cargada) return@LaunchedEffect

        when {

            // Sesión existente
            rutaActual == AppScreens.SPLASH &&
                    sesion.idUsuario != null -> {

                navController.navigate(AppScreens.INICIO) {
                    popUpTo(AppScreens.SPLASH) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            // No existe sesión
            rutaActual == AppScreens.SPLASH &&
                    sesion.idUsuario == null -> {

                navController.navigate(AppScreens.LOGIN) {
                    popUpTo(AppScreens.SPLASH) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            // Login exitoso
            rutaActual == AppScreens.LOGIN &&
                    sesion.idUsuario != null -> {

                navController.navigate(AppScreens.INICIO) {
                    popUpTo(AppScreens.LOGIN) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            // Sesión cerrada: impedir acceso a pantallas protegidas
            sesion.idUsuario == null &&
                    rutaActual != null &&
                    rutaActual !in setOf(
                AppScreens.LOGIN,
                AppScreens.SPLASH
            ) -> {

                navController.navigate(AppScreens.LOGIN) {
                    popUpTo(AppScreens.INICIO) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
    }

    // Navegación principal
    NavHost(
        navController = navController,
        startDestination = AppScreens.SPLASH
    ) {

        composable(AppScreens.SPLASH) {
            PantallaCarga()
        }

        composable(AppScreens.LOGIN) {
            LoginScreen(
                viewModel = usuarioViewModel
            )
        }

        composable(AppScreens.INICIO) {
            PrimeraPantalla(navController)
        }

        composable(AppScreens.MAPA) {
            SegundaPantalla(navController)
        }

        composable(AppScreens.REGISTRAR) {
            RegistrarGatoScreen(navController)
        }

        composable(AppScreens.COLECCION) {
            ColeccionScreen(
                navController = navController,
                idUsuario = sesion.idUsuario
            )
        }

        composable(AppScreens.PERFIL) {
            PerfilScreen(
                navController = navController,
                idUsuario = sesion.idUsuario,
                usuarioViewModel = usuarioViewModel
            )
        }
    }
}

// Pantalla de carga inicial
@Composable
private fun PantallaCarga() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Preparando GatoGO…",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Barra de navegación inferior
@Composable
fun BarraNavegacion(
    navController: NavHostController?,
    rutaActual: String
) {

    val destinos = listOf(
        AppScreens.INICIO,
        AppScreens.MAPA,
        AppScreens.REGISTRAR,
        AppScreens.COLECCION,
        AppScreens.PERFIL
    )

    val nombres = listOf(
        "Inicio",
        "Mapa",
        "Registrar",
        "Colección",
        "Perfil"
    )

    val iconos = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Map,
        Icons.Outlined.AddCircleOutline,
        Icons.Outlined.GridView,
        Icons.Outlined.PersonOutline
    )

    NavigationBar {

        destinos.forEachIndexed { index, destino ->

            NavigationBarItem(
                selected = rutaActual == destino,

                onClick = {
                    navController?.let { controller ->

                        if (rutaActual != destino) {

                            controller.navigate(destino) {

                                popUpTo(AppScreens.INICIO) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                },

                icon = {
                    Icon(
                        imageVector = iconos[index],
                        contentDescription = nombres[index]
                    )
                },

                label = {
                    Text(nombres[index])
                }
            )
        }
    }
}

// Obtener la ruta actual
@Composable
fun RutaActual(
    navController: NavHostController
): String {

    val entry by navController.currentBackStackEntryAsState()

    return entry?.destination?.route
        ?: AppScreens.INICIO
}
