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
                "GTA VI: Rockstar confirma lanzamiento para 2025",
                "15 Nov 2025",
                "Después de años de espera, Grand Theft Auto VI finalmente tiene fecha oficial.",
                "Lanzamientos"
            ),
            NewsItem(
                "Black Friday Gaming: Hasta 70% de descuento",
                "22 Nov 2025",
                "Las mejores ofertas en juegos, periféricos y hardware esta semana del Black Friday.",
                "Ofertas"
            ),
            NewsItem(
                "The Game Awards 2025: Nominees revelados",
                "10 Nov 2025",
                "Baldur's Gate 3 y Zelda: Tears of the Kingdom lideran las nominaciones este año.",
                "Eventos"
            ),
            NewsItem(
                "PlayStation 6: Primeros rumores y especificaciones",
                "05 Nov 2025",
                "Sony estaría trabajando en la siguiente generación de consolas con tecnología de IA.",
                "Hardware"
            ),
            NewsItem(
                "Cyberpunk 2077: Expansion Phantom Liberty arrasa en ventas",
                "28 Oct 2025",
                "CD Projekt Red celebra el éxito rotundo de su última expansión con críticas excelentes.",
                "Lanzamientos"
            ),
            NewsItem(
                "Steam Winter Sale 2025: Fechas confirmadas",
                "20 Oct 2025",
                "Valve anuncia las fechas para las rebajas de invierno con miles de juegos en oferta.",
                "Ofertas"
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
