package com.levelup.gamer.repository.auth

import com.levelup.gamer.model.UserEntity

class AuthRepository(private val userDao: UserDao) {
    
    suspend fun login(email: String, password: String): UserEntity? {
        return userDao.login(email, password)
    }
    
    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
    
    suspend fun register(user: UserEntity) {
        userDao.insertUser(user)
    }
    
    suspend fun initializeDefaultUsers() {
        val userCount = userDao.getUserCount()
        if (userCount == 0) {
            // Insertar usuarios por defecto
            userDao.insertUser(
                UserEntity(
                    email = "admin@levelup.com",
                    password = "admin123",
                    displayName = "Administrador",
                    isAdmin = true
                )
            )
            userDao.insertUser(
                UserEntity(
                    email = "usuario@levelup.com",
                    password = "user123",
                    displayName = "Usuario Gamer",
                    isAdmin = false
                )
            )
        }
    }
}
