package com.levelup.gamer.network.api

import com.levelup.gamer.network.ApiConstants
import com.levelup.gamer.network.dto.AuthResponse
import com.levelup.gamer.network.dto.LoginRequest
import com.levelup.gamer.network.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {
    
    @POST(ApiConstants.Endpoints.AUTH_LOGIN)
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @POST(ApiConstants.Endpoints.AUTH_REGISTER)
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
    
    @POST(ApiConstants.Endpoints.AUTH_VALIDATE)
    suspend fun validateToken(@Header("Authorization") token: String): Response<AuthResponse>
}
