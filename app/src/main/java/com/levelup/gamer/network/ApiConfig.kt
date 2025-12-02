package com.levelup.gamer.network

object ApiConfig {
    // ========== CONFIGURACIÓN DE MODO ===========
    // Cambia esto a true cuando el backend esté funcionando
    const val BACKEND_ENABLED = true  // ✅ TRUE = usa backend, FALSE = usa Room local
    
    // URL base del API Gateway
    // EMULADOR: usa 10.0.2.2 (apunta al localhost de tu PC)
    // DISPOSITIVO FÍSICO: usa la IP de tu PC en la red WiFi
    // TEMPORAL: Apuntando directo al Auth service (8081) hasta que API Gateway esté disponible
    const val BASE_URL = "http://10.0.2.2:8081/"
    
    // URLs alternativas para diferentes entornos
    const val BASE_URL_LOCAL = "http://10.0.2.2:8080/"  // Emulador
    const val BASE_URL_DEVICE = "http://192.168.1.XXX:8080/"  // Dispositivo físico (cambiar XXX)
    const val BASE_URL_PRODUCTION = "https://api.levelupgamer.com/"  // Producción
    
    // Endpoints de los microservicios (según tu backend real)
    object Endpoints {
        // Autenticación (Puerto 8081)
        const val REGISTER = "api/auth/register"
        const val LOGIN = "api/auth/login"
        const val VALIDATE = "api/auth/validate"
        const val AUTH_HEALTH = "api/auth/health"
        
        // Usuarios (Puerto 8082)
        const val USUARIOS = "api/usuarios"
        const val USUARIO_BY_ID = "api/usuarios/{id}"
        
        // Productos (Puerto 8083)
        const val PRODUCTOS = "api/productos"
        const val PRODUCTOS_DESTACADOS = "api/productos/destacados"
        const val PRODUCTOS_CATEGORIA = "api/productos/categoria/{categoria}"
        const val PRODUCTOS_BUSCAR = "api/productos/buscar"
        const val PRODUCTO_BY_ID = "api/productos/{id}"
        const val PRODUCTO_STOCK = "api/productos/{id}/stock"
        const val PRODUCTO_ACTIVAR = "api/productos/{id}/activar"
        const val PRODUCTO_PERMANENTE = "api/productos/{id}/permanente"
        const val PRODUCTOS_HEALTH = "api/productos/health"
        
        // Órdenes/Pedidos (Puerto 8084)
        const val ORDENES = "api/ordenes"
        const val ORDEN_BY_ID = "api/ordenes/{id}"
        const val ORDENES_USUARIO = "api/ordenes/usuario/{usuarioId}"
    }
    
    // Timeouts
    const val CONNECT_TIMEOUT = 30L // segundos
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}
