package com.levelup.gamer.model

/**
 * Modelo de datos para un Usuario
 * 
 * Representa la información básica de un usuario en la app.
 * En esta versión simplificada no usamos Firebase, solo almacenamiento local.
 * 
 * @property uid Identificador único del usuario
 * @property email Correo electrónico del usuario
 * @property displayName Nombre para mostrar (opcional)
 * @property photoUrl URL de la foto de perfil (opcional)
 */
data class User(
    val uid: String,
    val email: String?,
    val displayName: String? = null,
    val photoUrl: String? = null
)
