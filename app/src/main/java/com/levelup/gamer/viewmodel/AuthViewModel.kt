package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.data.UserPreferences
import com.levelup.gamer.model.UserEntity
import com.levelup.gamer.repository.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val currentUser: UserEntity? = null,
    val error: String? = null
)

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    init {
        initializeUsers()
        restoreSession()
    }
    
    private fun initializeUsers() {
        viewModelScope.launch {
            authRepository.initializeDefaultUsers()
        }
    }
    
    private fun restoreSession() {
        viewModelScope.launch {
            val userId = userPreferences.userIdFlow.first()
            if (userId != null) {
                val user = authRepository.getUserById(userId)
                if (user != null) {
                    _authState.value = _authState.value.copy(currentUser = user)
                }
            }
        }
    }
    
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            try {
                val result = authRepository.loginWithBackend(email.trim(), password)
                
                result.onSuccess { user ->
                    userPreferences.saveUser(user.id, user.email, user.nombre)
                    
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        currentUser = user,
                        error = null
                    )
                    onSuccess()
                }.onFailure { backendException ->
                    val localUser = authRepository.login(email.trim(), password)
                    
                    if (localUser != null) {
                        userPreferences.saveUser(localUser.id, localUser.email, localUser.nombre)
                        
                        _authState.value = _authState.value.copy(
                            isLoading = false,
                            currentUser = localUser,
                            error = null
                        )
                        onSuccess()
                    } else {
                        _authState.value = _authState.value.copy(
                            isLoading = false,
                            error = "Credenciales incorrectas o sin conexión"
                        )
                    }
                }
            } catch (e: Exception) {
                val localUser = authRepository.login(email.trim(), password)
                
                if (localUser != null) {
                    userPreferences.saveUser(localUser.id, localUser.email, localUser.nombre)
                    
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        currentUser = localUser,
                        error = null
                    )
                    onSuccess()
                } else {
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = "Sin conexión y credenciales no encontradas localmente"
                    )
                }
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            userPreferences.clearUser()
            _authState.value = _authState.value.copy(currentUser = null)
        }
    }
    
    suspend fun registerUser(email: String, password: String, nombre: String): UserEntity? {
        return try {
            // Verificar si el email ya existe
            val existingUser = authRepository.getUserByEmail(email.trim())
            if (existingUser != null) {
                throw Exception("Este correo ya está registrado")
            }
            
            // Registrar nuevo usuario (el descuento se asigna automáticamente en el repository)
            val newUser = authRepository.register(email.trim(), password, nombre.trim())
            
            // Guardar sesión
            userPreferences.saveUser(newUser.id, newUser.email, newUser.nombre)
            
            // Actualizar estado
            _authState.value = _authState.value.copy(currentUser = newUser)
            
            newUser
        } catch (e: Exception) {
            throw e
        }
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
}
