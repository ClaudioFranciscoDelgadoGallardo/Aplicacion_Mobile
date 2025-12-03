package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AdminUiState(
    val productos: List<Producto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class AdminViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "Funcionalidad de administración pendiente de implementar en backend"
            )
        }
    }

    fun agregarStock(codigo: String, cantidad: Int) {
    }

    fun quitarStock(codigo: String, cantidad: Int) {
    }

    fun actualizarStock(codigo: String, nuevoStock: Int) {
    }

    fun agregarProducto(producto: Producto) {
    }

    fun eliminarProducto(codigo: String) {
    }

    fun limpiarMensajes() {
        _uiState.value = _uiState.value.copy(
            successMessage = null,
            error = null
        )
    }
}
