package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProductDetailUiState(
    val producto: Producto? = null,
    val quantity: Int = 1,
    val isAddedToCart: Boolean = false,
    val error: String? = null
)

class ProductDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun setProducto(producto: Producto) {
        _uiState.value = _uiState.value.copy(producto = producto)
    }

    fun incrementQuantity() {
        _uiState.value = _uiState.value.copy(
            quantity = _uiState.value.quantity + 1
        )
    }

    fun decrementQuantity() {
        if (_uiState.value.quantity > 1) {
            _uiState.value = _uiState.value.copy(
                quantity = _uiState.value.quantity - 1
            )
        }
    }

    fun resetQuantity() {
        _uiState.value = _uiState.value.copy(quantity = 1)
    }

    fun markAsAddedToCart() {
        _uiState.value = _uiState.value.copy(isAddedToCart = true)
    }

    fun resetAddedToCartFlag() {
        _uiState.value = _uiState.value.copy(isAddedToCart = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
