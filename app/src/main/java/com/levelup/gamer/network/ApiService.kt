package com.levelup.gamer.network

import com.levelup.gamer.network.models.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    
    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>
    
    @POST("api/auth/validate")
    suspend fun validateToken(@Header("Authorization") token: String): Response<UsuarioDto>
    
    @GET("api/auth/health")
    suspend fun authHealth(): Response<Map<String, String>>
    
    @GET("api/productos")
    suspend fun getProductos(): Response<List<ProductoDto>>
    
    @GET("api/productos/destacados")
    suspend fun getProductosDestacados(): Response<List<ProductoDto>>
    
    @GET("api/productos/categoria/{categoria}")
    suspend fun getProductosByCategoria(@Path("categoria") categoria: String): Response<List<ProductoDto>>
    
    @GET("api/productos/buscar")
    suspend fun buscarProductos(@Query("nombre") nombre: String): Response<List<ProductoDto>>
    
    @GET("api/productos/{id}")
    suspend fun getProductoById(@Path("id") id: Long): Response<ProductoDto>
    
    @POST("api/productos")
    suspend fun createProducto(
        @Header("Authorization") token: String,
        @Body producto: ProductoDto
    ): Response<ProductoDto>
    
    @PUT("api/productos/{id}")
    suspend fun updateProducto(
        @Path("id") id: Long,
        @Header("Authorization") token: String,
        @Body producto: ProductoDto
    ): Response<ProductoDto>
    
    @PATCH("api/productos/{id}/stock")
    suspend fun updateStock(
        @Path("id") id: Long,
        @Query("cantidad") cantidad: Int,
        @Header("Authorization") token: String
    ): Response<ProductoDto>
    
    @DELETE("api/productos/{id}")
    suspend fun desactivarProducto(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Response<Void>
    
    @PUT("api/productos/{id}/activar")
    suspend fun activarProducto(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Response<ProductoDto>
    
    @DELETE("api/productos/{id}/permanente")
    suspend fun eliminarProductoPermanente(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Response<Void>
    
    @GET("api/productos/health")
    suspend fun productosHealth(): Response<Map<String, String>>
    
    @GET("api/usuarios")
    suspend fun getUsuarios(@Header("Authorization") token: String): Response<List<UsuarioDto>>
    
    @GET("api/usuarios/{id}")
    suspend fun getUsuarioById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Response<UsuarioDto>
    
    @PUT("api/usuarios/{id}")
    suspend fun updateUsuario(
        @Path("id") id: Long,
        @Header("Authorization") token: String,
        @Body usuario: UsuarioDto
    ): Response<UsuarioDto>
    
    @POST("api/ordenes")
    suspend fun createOrden(
        @Header("Authorization") token: String,
        @Body orden: OrdenDto
    ): Response<OrdenDto>
    
    @GET("api/ordenes/{id}")
    suspend fun getOrdenById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): Response<OrdenDto>
    
    @GET("api/ordenes/usuario/{usuarioId}")
    suspend fun getOrdenesByUsuario(
        @Path("usuarioId") usuarioId: Long,
        @Header("Authorization") token: String
    ): Response<List<OrdenDto>>
}

