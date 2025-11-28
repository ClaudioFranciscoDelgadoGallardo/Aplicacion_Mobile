package com.levelup.gamer.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.repository.favoritos.FavoritosRepository
import com.levelup.gamer.repository.pedido.PedidoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val userName: String = "",
    val userEmail: String = "",
    val profileImageUri: Uri? = null,
    val cameraPermissionGranted: Boolean = false,
    val cameraPermissionDenied: Boolean = false,
    val showSuccessMessage: Boolean = false,
    val purchaseCount: Int = 0,
    val favoriteCount: Int = 0,
    val points: Int = 0,
    val isLoading: Boolean = true
)

class ProfileViewModel(
    private val authViewModel: AuthViewModel,
    private val favoritosRepository: FavoritosRepository,
    private val pedidoDao: PedidoDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val currentUser = authViewModel.authState.value.currentUser
            
            if (currentUser != null) {
                // Cargar estadísticas de compras
                val pedidos = pedidoDao.getPedidosByUserId(currentUser.id)
                val purchaseCount = pedidos.size
                
                // Calcular puntos totales sumando los puntos de todos los pedidos
                val puntosGanados = pedidos.sumOf { it.puntosGanados }
                
                // Cargar cantidad de favoritos
                val favoriteCount = favoritosRepository.getFavoritosCount(currentUser.id)
                
                _uiState.value = _uiState.value.copy(
                    userName = currentUser.nombre,
                    userEmail = currentUser.email,
                    points = puntosGanados,
                    purchaseCount = purchaseCount,
                    favoriteCount = favoriteCount,
                    isLoading = false
                )
            } else {
                _uiState.value = ProfileUiState(isLoading = false)
            }
        }
    }

    fun refreshProfile() {
        loadUserProfile()
    }

    fun updateProfileImage(uri: Uri) {
        _uiState.value = _uiState.value.copy(
            profileImageUri = uri,
            showSuccessMessage = true
        )
    }

    fun updateUserName(name: String) {
        _uiState.value = _uiState.value.copy(userName = name)
    }

    fun updateUserEmail(email: String) {
        _uiState.value = _uiState.value.copy(userEmail = email)
    }

    fun setCameraPermissionGranted(granted: Boolean) {
        _uiState.value = _uiState.value.copy(
            cameraPermissionGranted = granted,
            cameraPermissionDenied = !granted
        )
    }

    fun setCameraPermissionDenied(denied: Boolean) {
        _uiState.value = _uiState.value.copy(cameraPermissionDenied = denied)
    }

    fun dismissSuccessMessage() {
        _uiState.value = _uiState.value.copy(showSuccessMessage = false)
    }

    fun updateStats(purchases: Int, favorites: Int, points: Int) {
        _uiState.value = _uiState.value.copy(
            purchaseCount = purchases,
            favoriteCount = favorites,
            points = points
        )
    }
}

class ProfileViewModelFactory(
    private val authViewModel: AuthViewModel,
    private val favoritosRepository: FavoritosRepository,
    private val pedidoDao: PedidoDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(authViewModel, favoritosRepository, pedidoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
