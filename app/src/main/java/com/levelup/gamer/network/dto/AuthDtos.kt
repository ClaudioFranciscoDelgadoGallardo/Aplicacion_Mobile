package com.levelup.gamer.network.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("correo")
    val correo: String,
    @SerializedName("password")
    val password: String
)

data class RegisterRequest(
    @SerializedName("correo")
    val correo: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("run")
    val run: String
)

data class AuthResponse(
    @SerializedName("token")
    val token: String?,
    
    @SerializedName("tipo")
    val tipo: String?,
    
    @SerializedName("id")
    val id: Long?,
    
    @SerializedName("nombre")
    val nombre: String?,
    
    @SerializedName("apellidos")
    val apellidos: String?,
    
    @SerializedName("correo")
    val correo: String?,
    
    @SerializedName("rol")
    val rol: String?,
    
    @SerializedName("mensaje")
    val mensaje: String?
)

data class UsuarioDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("correo")
    val correo: String?,
    @SerializedName("nombre")
    val nombre: String?,
    @SerializedName("run")
    val run: String?,
    @SerializedName("rol")
    val rol: String?
)
