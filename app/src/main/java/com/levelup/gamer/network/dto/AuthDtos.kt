package com.levelup.gamer.network.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("correo")
    val correo: String,
    @SerializedName("password")
    val password: String
)

data class RegisterRequest(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellidos")
    val apellidos: String,
    @SerializedName("correo")
    val correo: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("telefono")
    val telefono: String? = null
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
