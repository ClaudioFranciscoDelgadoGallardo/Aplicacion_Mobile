package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.Producto
import com.levelup.gamer.network.RetrofitClient
import com.levelup.gamer.network.mappers.toProducto
import com.levelup.gamer.network.mappers.toProductos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProductoUiState {
    object Loading : ProductoUiState()
    data class Success(val productos: List<Producto>) : ProductoUiState()
    data class Error(val message: String) : ProductoUiState()
}

class ProductoViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService

    private val _uiState = MutableStateFlow<ProductoUiState>(ProductoUiState.Loading)
    val uiState: StateFlow<ProductoUiState> = _uiState.asStateFlow()

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            _uiState.value = ProductoUiState.Loading

            try {
                val response = apiService.getProductos()

                if (response.isSuccessful && response.body() != null) {
                    val productos = response.body()!!.toProductos()
                    _productos.value = productos
                    _uiState.value = ProductoUiState.Success(productos)
                } else {
                    _uiState.value = ProductoUiState.Error("Error del servidor: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = ProductoUiState.Error("Error de conexión: ${e.message}")
            }
        }
    }

    fun buscarProductos(query: String) {
        if (query.isBlank()) {
            cargarProductos()
            return
        }

        viewModelScope.launch {
            _uiState.value = ProductoUiState.Loading

            try {
                val response = apiService.buscarProductos(query)

                if (response.isSuccessful && response.body() != null) {
                    val productos = response.body()!!.toProductos()
                    _productos.value = productos
                    _uiState.value = ProductoUiState.Success(productos)
                } else {
                    _uiState.value = ProductoUiState.Error("No se encontraron resultados")
                }
            } catch (e: Exception) {
                _uiState.value = ProductoUiState.Error("Error de búsqueda: ${e.message}")
            }
        }
    }

    fun filtrarPorCategoria(categoria: String) {
        viewModelScope.launch {
            _uiState.value = ProductoUiState.Loading

            try {
                val response = apiService.getProductosByCategoria(categoria)

                if (response.isSuccessful && response.body() != null) {
                    val productos = response.body()!!.toProductos()
                    _productos.value = productos
                    _uiState.value = ProductoUiState.Success(productos)
                } else {
                    _uiState.value = ProductoUiState.Error("No se encontraron productos")
                }
            } catch (e: Exception) {
                _uiState.value = ProductoUiState.Error("Error: ${e.message}")
            }
        }
    }

    fun obtenerProductoPorCodigo(codigo: String, onResult: (Producto?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getProductos()

                if (response.isSuccessful && response.body() != null) {
                    val producto = response.body()!!
                        .find { it.codigo == codigo }
                        ?.toProducto()
                    onResult(producto)
                } else {
                    onResult(null)
                }
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun retry() {
        cargarProductos()
    }

    fun obtenerDestacados(): List<Producto> {
        return _productos.value.take(6)
    }
}

