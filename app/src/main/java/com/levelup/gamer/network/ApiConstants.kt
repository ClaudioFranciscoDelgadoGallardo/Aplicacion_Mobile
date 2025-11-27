package com.levelup.gamer.network

object ApiConstants {
    const val BASE_URL = "http://10.0.2.2:8080/"
    
    object Endpoints {
        const val AUTH_LOGIN = "api/auth/login"
        const val AUTH_REGISTER = "api/auth/register"
        const val AUTH_VALIDATE = "api/auth/validate"
        
        const val PRODUCTOS = "api/productos"
        const val PRODUCTOS_DESTACADOS = "api/productos/destacados"
        const val PRODUCTOS_CATEGORIA = "api/productos/categoria/{categoriaId}"
        const val PRODUCTOS_BUSCAR = "api/productos/buscar"
        const val PRODUCTO_BY_ID = "api/productos/{id}"
    }
}
