package com.example.gatogo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gatogo.R
import com.example.gatogo.ui.viewmodel.UsuarioViewModel

@Composable
fun LoginScreen(viewModel: UsuarioViewModel) {
    var correo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var mostrarPassword by rememberSaveable { mutableStateOf(false) }
    var aceptaPrivacidad by rememberSaveable { mutableStateOf(false) }
    var dialogoLegal by rememberSaveable { mutableStateOf(0) }
    val acceso by viewModel.acceso.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(padding)
                .padding(horizontal = 28.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.login_logo_desc),
                modifier = Modifier.size(112.dp)
            )
            Text(
                text = stringResource(R.string.login_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.login_email)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                label = { Text(stringResource(R.string.login_password)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (mostrarPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                        Icon(
                            imageVector = if (mostrarPassword) Icons.Outlined.VisibilityOff
                            else Icons.Outlined.Visibility,
                            contentDescription = stringResource(
                                if (mostrarPassword) R.string.login_hide_password
                                else R.string.login_show_password
                            )
                        )
                    }
                }
            )
            if (acceso.errorRes != null) {
                Text(
                    text = stringResource(acceso.errorRes!!),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = aceptaPrivacidad,
                    onCheckedChange = { aceptaPrivacidad = it }
                )
                Text(
                    text = stringResource(R.string.privacy_accept_label),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = stringResource(R.string.privacy_intro),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(onClick = { dialogoLegal = 1 }) {
                    Text(stringResource(R.string.privacy_terms_link))
                }
                TextButton(onClick = { dialogoLegal = 2 }) {
                    Text(stringResource(R.string.privacy_policy_link))
                }
            }
            Button(
                onClick = { viewModel.iniciarSesionOCrearCuenta(correo, password) },
                enabled = !acceso.cargando && aceptaPrivacidad,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
            ) {
                if (acceso.cargando) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(stringResource(R.string.login_enter))
                }
            }
            Text(
                text = stringResource(R.string.login_local_note),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 14.dp)
            )
            Text(
                text = stringResource(R.string.login_sync_note),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    if (dialogoLegal == 1 || dialogoLegal == 2) {
        val esTerminos = dialogoLegal == 1
        AlertDialog(
            onDismissRequest = { dialogoLegal = 0 },
            title = {
                Text(stringResource(if (esTerminos) R.string.privacy_terms_title else R.string.privacy_policy_title))
            },
            text = {
                Text(stringResource(if (esTerminos) R.string.privacy_terms_body else R.string.privacy_policy_body))
            },
            confirmButton = {
                TextButton(onClick = { dialogoLegal = 0 }) {
                    Text(stringResource(R.string.common_close))
                }
            },
        )
    }
}
