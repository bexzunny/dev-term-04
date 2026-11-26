package com.example.gatogo.ui.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.gatogo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ImagenLocal(uriTexto: String?, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bitmap = produceState<Bitmap?>(initialValue = null, uriTexto) {
        value = withContext(Dispatchers.IO) {
            if (uriTexto.isNullOrBlank()) null
            else runCatching {
                context.contentResolver.openInputStream(Uri.parse(uriTexto))?.use { input ->
                    val options = BitmapFactory.Options().apply { inSampleSize = 4 }
                    BitmapFactory.decodeStream(input, null, options)
                }
            }.getOrNull()
        }
    }
    if (bitmap.value != null) {
        Image(
            bitmap = bitmap.value!!.asImageBitmap(),
            contentDescription = stringResource(R.string.register_photo_selected),
            modifier = modifier
        )
    } else {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.photo_preview_unavailable),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
