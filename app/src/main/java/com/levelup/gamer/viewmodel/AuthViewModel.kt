package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.UserEntity
import com.levelup.gamer.repository.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val currentUser: UserEntity? = null,
    val error: String? = null
)

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    init {
        initializeUsers()
    }
    
    private fun initializeUsers() {
        viewModelScope.launch {
            authRepository.initializeDefaultUsers()
        }
    }
    
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            try {
                val user = authRepository.login(email.trim(), password)
                
                if (user != null) {
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        currentUser = user,
                        error = null
                    )
                    onSuccess()
                } else {
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = "Email o contraseña incorrectos"
                    )
                }
            } catch (e: Exception) {
                _authState.value = _authState.value.copy(
                    isLoading = false,
                    error = "Error al iniciar sesión: ${e.message}"
                )
            }
        }
    }
    
    fun logout() {
        _authState.value = _authState.value.copy(currentUser = null)
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
}
