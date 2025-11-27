package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.CarritoItem
import com.levelup.gamer.repository.carrito.CarritoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CartUiState(
    val items: List<CarritoItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val itemCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CartViewModel(
    private val carritoRepository: CarritoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        observeCart()
    }

    private fun observeCart() {
        viewModelScope.launch {
            carritoRepository.itemsCarrito.collect { items ->
                _uiState.value = _uiState.value.copy(items = items)
            }
        }

        viewModelScope.launch {
            carritoRepository.totalCarrito.collect { total ->
                _uiState.value = _uiState.value.copy(totalAmount = total ?: 0.0)
            }
        }

        viewModelScope.launch {
            carritoRepository.cantidadItems.collect { count ->
                _uiState.value = _uiState.value.copy(itemCount = count)
            }
        }
    }

    fun incrementQuantity(item: CarritoItem) {
        viewModelScope.launch {
            try {
                carritoRepository.incrementarCantidad(item)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al incrementar cantidad: ${e.message}"
                )
            }
        }
    }

    fun decrementQuantity(item: CarritoItem) {
        viewModelScope.launch {
            try {
                carritoRepository.decrementarCantidad(item)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al decrementar cantidad: ${e.message}"
                )
            }
        }
    }

    fun removeItem(item: CarritoItem) {
        viewModelScope.launch {
            try {
                carritoRepository.eliminarItem(item)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al eliminar item: ${e.message}"
                )
            }
        }
    }

    fun checkout() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                carritoRepository.vaciarCarrito()
                _uiState.value = _uiState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al finalizar compra: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
