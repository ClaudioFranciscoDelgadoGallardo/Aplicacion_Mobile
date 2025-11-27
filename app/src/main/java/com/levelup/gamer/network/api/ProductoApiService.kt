package com.levelup.gamer.network.api

import com.levelup.gamer.network.ApiConstants
import com.levelup.gamer.network.dto.ProductoDto
import retrofit2.Response
import retrofit2.http.*

interface ProductoApiService {
    
    @GET(ApiConstants.Endpoints.PRODUCTOS)
    suspend fun obtenerTodos(): Response<List<ProductoDto>>
    
    @GET(ApiConstants.Endpoints.PRODUCTOS_DESTACADOS)
    suspend fun obtenerDestacados(): Response<List<ProductoDto>>
    
    @GET(ApiConstants.Endpoints.PRODUCTOS_CATEGORIA)
    suspend fun buscarPorCategoria(@Path("categoriaId") categoriaId: Long): Response<List<ProductoDto>>
    
    @GET(ApiConstants.Endpoints.PRODUCTOS_BUSCAR)
    suspend fun buscarPorNombre(@Query("nombre") nombre: String): Response<List<ProductoDto>>
    
    @GET(ApiConstants.Endpoints.PRODUCTO_BY_ID)
    suspend fun obtenerPorId(@Path("id") id: Long): Response<ProductoDto>
}
