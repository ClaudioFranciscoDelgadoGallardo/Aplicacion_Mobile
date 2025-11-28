package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.Producto
import com.levelup.gamer.repository.ProductoRepository
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

class AdminViewModel(
    private val productoRepository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val productos = productoRepository.obtenerTodosLosProductos()
                _uiState.value = _uiState.value.copy(
                    productos = productos,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error al cargar productos: ${e.message}"
                )
            }
        }
    }

    fun agregarStock(codigo: String, cantidad: Int) {
        viewModelScope.launch {
            val producto = _uiState.value.productos.find { it.codigo == codigo }
            if (producto != null) {
                val stockActual = producto.stock.toIntOrNull() ?: 0
                val nuevoStock = stockActual + cantidad
                actualizarStock(codigo, nuevoStock)
            }
        }
    }

    fun quitarStock(codigo: String, cantidad: Int) {
        viewModelScope.launch {
            val producto = _uiState.value.productos.find { it.codigo == codigo }
            if (producto != null) {
                val stockActual = producto.stock.toIntOrNull() ?: 0
                val nuevoStock = maxOf(0, stockActual - cantidad)
                actualizarStock(codigo, nuevoStock)
            }
        }
    }

    fun actualizarStock(codigo: String, nuevoStock: Int) {
        viewModelScope.launch {
            try {
                productoRepository.actualizarStock(codigo, nuevoStock)
                
                // Actualizar el producto en la lista local
                val productosActualizados = _uiState.value.productos.map { producto ->
                    if (producto.codigo == codigo) {
                        producto.copy(stock = nuevoStock.toString())
                    } else {
                        producto
                    }
                }
                
                _uiState.value = _uiState.value.copy(
                    productos = productosActualizados,
                    successMessage = "Stock actualizado correctamente"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al actualizar stock: ${e.message}"
                )
            }
        }
    }

    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                productoRepository.agregarProducto(producto)
                
                // Agregar el producto a la lista local
                val productosActualizados = _uiState.value.productos + producto
                
                _uiState.value = _uiState.value.copy(
                    productos = productosActualizados,
                    successMessage = "Producto agregado correctamente"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al agregar producto: ${e.message}"
                )
            }
        }
    }

    fun eliminarProducto(codigo: String) {
        viewModelScope.launch {
            try {
                productoRepository.eliminarProducto(codigo)
                
                // Eliminar el producto de la lista local
                val productosActualizados = _uiState.value.productos.filter { it.codigo != codigo }
                
                _uiState.value = _uiState.value.copy(
                    productos = productosActualizados,
                    successMessage = "Producto eliminado correctamente"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al eliminar producto: ${e.message}"
                )
            }
        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            error = null,
            successMessage = null
        )
    }
}
