package com.levelup.gamer.network

import com.levelup.gamer.model.Producto
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("productos")
    suspend fun getProductos(): Response<List<Producto>>

    @GET("productos/{id}")
    suspend fun getProducto(@Path("id") id: Long): Response<Producto>

    @GET("productos/codigo/{codigo}")
    suspend fun getProductoByCodigo(@Path("codigo") codigo: String): Response<Producto>

    @GET("productos/categoria/{categoria}")
    suspend fun getProductosByCategoria(@Path("categoria") categoria: String): Response<List<Producto>>

    @GET("productos/buscar")
    suspend fun buscarProductos(@Query("query") query: String): Response<List<Producto>>

    @POST("productos")
    suspend fun createProducto(@Body producto: Producto): Response<Producto>

    @PUT("productos/{id}")
    suspend fun updateProducto(
        @Path("id") id: Long,
        @Body producto: Producto
    ): Response<Producto>

    @DELETE("productos/{id}")
    suspend fun deleteProducto(@Path("id") id: Long): Response<Void>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<Usuario>

    @GET("auth/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<Usuario>
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val usuario: Usuario
)

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String
)

data class Usuario(
    val id: Long? = null,
    val nombre: String,
    val email: String,
    val password: String? = null,
    val fechaRegistro: String? = null
)

