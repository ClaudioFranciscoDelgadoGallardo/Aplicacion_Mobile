package com.levelup.gamer.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProfileUiState(
    val userName: String = "Gamer Pro",
    val userEmail: String = "usuariolvlup@lvlup.cl",
    val profileImageUri: Uri? = null,
    val cameraPermissionGranted: Boolean = false,
    val cameraPermissionDenied: Boolean = false,
    val showSuccessMessage: Boolean = false,
    val purchaseCount: Int = 12,
    val favoriteCount: Int = 34,
    val points: Int = 2450
)

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

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
