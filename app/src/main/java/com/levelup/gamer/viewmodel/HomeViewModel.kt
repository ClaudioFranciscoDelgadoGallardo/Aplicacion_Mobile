package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.Producto
import com.levelup.gamer.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val productos: List<Producto> = emptyList(),
    val productosFiltrados: List<Producto> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: String? = null,
    val isSearchActive: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(
    private val productoRepository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProductos()
    }

    private fun loadProductos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            val productosLocales = productoRepository.obtenerProductosDestacados()
            _uiState.value = _uiState.value.copy(
                productos = productosLocales,
                productosFiltrados = productosLocales,
                isLoading = false
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            productosFiltrados = filterProducts(query, _uiState.value.selectedCategory)
        )
    }

    fun setSearchActive(isActive: Boolean) {
        _uiState.value = _uiState.value.copy(
            isSearchActive = isActive,
            searchQuery = if (!isActive) "" else _uiState.value.searchQuery,
            productosFiltrados = if (!isActive) filterProducts("", _uiState.value.selectedCategory) else _uiState.value.productosFiltrados
        )
    }

    fun filterByCategory(category: String?) {
        _uiState.value = _uiState.value.copy(
            selectedCategory = category,
            productosFiltrados = filterProducts(_uiState.value.searchQuery, category)
        )
    }

    private fun filterProducts(query: String, category: String?): List<Producto> {
        var filteredList = _uiState.value.productos
        
        if (!category.isNullOrEmpty()) {
            filteredList = filteredList.filter { producto ->
                producto.categoria.equals(category, ignoreCase = true)
            }
        }
        
        if (query.isNotEmpty()) {
            filteredList = filteredList.filter { producto ->
                producto.nombre.contains(query, ignoreCase = true) ||
                producto.descripcionCorta.contains(query, ignoreCase = true) ||
                producto.categoria.contains(query, ignoreCase = true)
            }
        }
        
        return filteredList
    }

    fun getFilteredProducts(): List<Producto> {
        return _uiState.value.productosFiltrados
    }
}
