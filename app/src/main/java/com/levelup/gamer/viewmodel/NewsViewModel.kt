package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NewsItem(
    val title: String,
    val date: String,
    val summary: String,
    val category: String
)

data class NewsUiState(
    val newsList: List<NewsItem> = emptyList(),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false
)

class NewsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init {
        loadNews()
    }

    private fun loadNews() {
        val news = listOf(
            NewsItem(
                "Nuevos lanzamientos de la temporada",
                "25 Oct 2025",
                "Descubre los juegos más esperados que llegarán este mes a todas las plataformas.",
                "Lanzamientos"
            ),
            NewsItem(
                "Ofertas especiales en consolas",
                "23 Oct 2025",
                "PlayStation 5 y Xbox Series X con descuentos increíbles por tiempo limitado.",
                "Ofertas"
            ),
            NewsItem(
                "Torneo Level-Up: Inscripciones abiertas",
                "20 Oct 2025",
                "Participa en nuestro torneo mensual y gana increíbles premios gaming.",
                "Eventos"
            ),
            NewsItem(
                "Llegaron las GPU RTX 5000",
                "18 Oct 2025",
                "La nueva generación de tarjetas gráficas NVIDIA ya está disponible en stock.",
                "Hardware"
            )
        )
        _uiState.value = _uiState.value.copy(newsList = news)
    }

    fun filterByCategory(category: String?) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun getFilteredNews(): List<NewsItem> {
        val category = _uiState.value.selectedCategory
        return if (category == null) {
            _uiState.value.newsList
        } else {
            _uiState.value.newsList.filter { it.category == category }
        }
    }
}
