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
                        nombre = "${authResponse.nombre ?: ""} ${authResponse.apellidos ?: ""}".trim(),
                        isAdmin = authResponse.rol == "ADMIN"
                    )
                    
                    userDao.insertUser(user)
                    
                    Result.success(user)
                } else {
                    Result.failure(Exception(authResponse.mensaje ?: "Error en login"))
                }
            } else {
                Result.failure(Exception("Error de autenticación"))
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
        userDao.insertUser(user)
    }
    
    suspend fun initializeDefaultUsers() {
        val userCount = userDao.getUserCount()
        if (userCount == 0) {
            userDao.insertUser(
                UserEntity(
                    email = "admin@levelup.com",
                    password = "admin123",
                    nombre = "Administrador",
                    isAdmin = true
                )
            )
            userDao.insertUser(
                UserEntity(
                    email = "usuario@levelup.com",
                    password = "user123",
                    nombre = "Usuario Gamer",
                    isAdmin = false
                )
            )
        }
    }
}
