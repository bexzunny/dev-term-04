package com.example.gatogo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatogo.domain.repository.AvistamientoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MapaViewModel @Inject constructor(
    repository: AvistamientoRepository
) : ViewModel() {
    val avistamientos = repository.obtenerAvistamientos().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )
}
