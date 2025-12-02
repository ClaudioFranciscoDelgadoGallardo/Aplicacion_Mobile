package com.levelup.gamer.repository.auth

import com.levelup.gamer.model.UserEntity
import com.levelup.gamer.network.RetrofitClient
import com.levelup.gamer.network.dto.LoginRequest
import com.levelup.gamer.network.dto.RegisterRequest

class AuthRepository(private val userDao: UserDao) {
    
    private val authApi = RetrofitClient.authApi
    
    suspend fun loginWithBackend(email: String, password: String): Result<UserEntity> {
        return try {
            val request = LoginRequest(correo = email, password = password)
            val response = authApi.login(request)
            
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                if (authResponse.token != null && authResponse.id != null) {
                    val user = UserEntity(
                        id = authResponse.id.toInt(),
                        email = authResponse.correo ?: email,
                        password = password,
                        nombre = authResponse.nombre ?: "",
                        isAdmin = authResponse.rol == "ADMIN"
                    )
                    
                    // Guardar en DB local
                    userDao.insertUser(user)
                    
                    Result.success(user)
                } else {
                    Result.failure(Exception("Respuesta inválida del servidor"))
                }
            } else {
                Result.failure(Exception("Error de autenticación: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun login(email: String, password: String): UserEntity? {
        return userDao.login(email, password)
    }
    
    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
    
    suspend fun getUserById(userId: Int): UserEntity? {
        return userDao.getUserById(userId)
    }
    
    suspend fun register(user: UserEntity) {
        // Calcular descuento basado en el email
        val descuento = UserEntity.calcularDescuentoPorEmail(user.email)
        val userConDescuento = user.copy(descuentoPorcentaje = descuento)
        userDao.insertUser(userConDescuento)
    }
    
    suspend fun register(email: String, password: String, nombre: String): UserEntity {
        val descuento = UserEntity.calcularDescuentoPorEmail(email)
        val user = UserEntity(
            email = email,
            password = password,
            nombre = nombre,
            isAdmin = false,
            descuentoPorcentaje = descuento
        )
        userDao.insertUser(user)
        return user
    }
    
    suspend fun initializeDefaultUsers() {
        val userCount = userDao.getUserCount()
        if (userCount == 0) {
            userDao.insertUser(
                UserEntity(
                    email = "admlvlup@lvlup.cl",
                    password = "admin123",
                    nombre = "Administrador",
                    isAdmin = true
                )
            )
            userDao.insertUser(
                UserEntity(
                    email = "usuariolvlup@lvlup.cl",
                    password = "user123",
                    nombre = "Usuario Gamer",
                    isAdmin = false
                )
            )
        }
    }
}
