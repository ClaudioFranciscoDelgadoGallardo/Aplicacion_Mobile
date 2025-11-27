package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Category(
    val name: String,
    val description: String,
    val productCount: Int
)

data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: String? = null
)

class CategoriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState: StateFlow<CategoriesUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        val categories = listOf(
            Category("Consolas", "PlayStation, Xbox y más", 2),
            Category("Juegos", "Los mejores títulos", 3),
            Category("Accesorios", "Periféricos gaming", 3),
            Category("PC Gaming", "Componentes y equipos", 4)
        )
        _uiState.value = _uiState.value.copy(categories = categories)
    }

    fun selectCategory(categoryName: String) {
        _uiState.value = _uiState.value.copy(selectedCategory = categoryName)
    }

    fun clearSelection() {
        _uiState.value = _uiState.value.copy(selectedCategory = null)
    }
}
