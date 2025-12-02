# 🚀 GUÍA RÁPIDA DE INTEGRACIÓN BACKEND → APP MÓVIL ANDROID

## ENDPOINTS REALES DE TU BACKEND

### 🔐 AUTH SERVICE (Puerto 8081)
**Base:** `http://10.0.2.2:8081/api/auth`

| Método | Endpoint | Body | Response |
|--------|----------|------|----------|
| POST | `/register` | `{correo, password, nombre, run}` | `{token, usuario{id,correo,nombre,rol}}` |
| POST | `/login` | `{correo, password}` | `{token, usuario{id,correo,nombre,rol}}` |
| POST | `/validate` | Header: `Authorization: Bearer {token}` | `{valid: true, usuario}` |
| GET | `/health` | - | `{status: "UP"}` |

### 📦 PRODUCT SERVICE (Puerto 8083)
**Base:** `http://10.0.2.2:8083/api/productos`

| Método | Endpoint | Params | Response |
|--------|----------|--------|----------|
| GET | `/` | - | `List<Producto>` (solo activos) |
| GET | `/destacados` | - | `List<Producto>` destacados |
| GET | `/categoria/{categoria}` | categoria | `List<Producto>` |
| GET | `/buscar?nombre={query}` | nombre | `List<Producto>` |
| GET | `/{id}` | id | `Producto` |
| POST | `/` | Producto | `Producto` creado |
| PUT | `/{id}` | ActualizarProductoRequest | `Producto` actualizado |
| DELETE | `/{id}` | - | Desactiva producto |
| DELETE | `/{id}/permanente` | - | Elimina permanentemente |
| PUT | `/{id}/activar` | - | Activa producto |
| PATCH | `/{id}/stock?cantidad={cant}` | cantidad | Actualiza stock |

### 👥 USER SERVICE (Puerto 8082)
**Base:** `http://10.0.2.2:8082/api/usuarios`

| Método | Endpoint | Response |
|--------|----------|----------|
| GET | `/` | `List<Usuario>` |
| GET | `/{id}` | `Usuario` |
| PUT | `/{id}` | Usuario actualizado |
| DELETE | `/{id}` | Usuario eliminado |

### 📋 ORDER SERVICE (Puerto 8084)
**Base:** `http://10.0.2.2:8084/api/ordenes`

| Método | Endpoint | Body | Response |
|--------|----------|------|----------|
| GET | `/` | - | `List<Orden>` |
| GET | `/usuario/{userId}` | - | `List<Orden>` del usuario |
| POST | `/` | `{usuarioId, items[], total}` | Orden creada |

---

## 🔧 CONFIGURACIÓN EN TU APP

### 1. Actualizar `ApiConfig.kt`

```kotlin
package com.levelup.gamer.network

object ApiConfig {
    // Para EMULADOR ANDROID
    const val BASE_URL = "http://10.0.2.2:8080/"
    
    // Para DISPOSITIVO FÍSICO (cambiar IP)
    // const val BASE_URL = "http://192.168.1.XXX:8080/"
    
    object Endpoints {
        // Auth
        const val REGISTER = "api/auth/register"
        const val LOGIN = "api/auth/login"
        const val VALIDATE = "api/auth/validate"
        
        // Productos
        const val PRODUCTOS = "api/productos"
        const val PRODUCTOS_DESTACADOS = "api/productos/destacados"
        const val PRODUCTOS_CATEGORIA = "api/productos/categoria/{cat}"
        const val PRODUCTOS_BUSCAR = "api/productos/buscar"
        const val PRODUCTO_ID = "api/productos/{id}"
        const val PRODUCTO_STOCK = "api/productos/{id}/stock"
        
        // Usuarios
        const val USUARIOS = "api/usuarios"
        const val USUARIO_ID = "api/usuarios/{id}"
        
        // Órdenes
        const val ORDENES = "api/ordenes"
        const val ORDENES_USUARIO = "api/ordenes/usuario/{userId}"
    }
}
```

### 2. DTOs actualizados (según tu backend)

```kotlin
// LoginRequest
data class LoginRequest(
    val correo: String,
    val password: String
)

// RegisterRequest
data class RegisterRequest(
    val correo: String,
    val password: String,
    val nombre: String,
    val run: String
)

// AuthResponse
data class AuthResponse(
    val token: String,
    val usuario: UsuarioDto
)

// UsuarioDto
data class UsuarioDto(
    val id: Long,
    val correo: String,
    val nombre: String,
    val rol: String, // "ADMIN" o "USER"
    val run: String?
)

// Producto (debe coincidir con tu backend)
data class ProductoDto(
    val id: Long? = null,
    val codigo: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val imagen: String?,
    val destacado: Boolean = false,
    val activo: Boolean = true
)
```

### 3. ApiService actualizado

```kotlin
interface ApiService {
    // AUTH
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
    
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @POST("api/auth/validate")
    suspend fun validateToken(@Header("Authorization") token: String): Response<AuthResponse>
    
    // PRODUCTOS
    @GET("api/productos")
    suspend fun getProductos(): Response<List<ProductoDto>>
    
    @GET("api/productos/destacados")
    suspend fun getDestacados(): Response<List<ProductoDto>>
    
    @GET("api/productos/categoria/{categoria}")
    suspend fun getByCategoria(@Path("categoria") categoria: String): Response<List<ProductoDto>>
    
    @GET("api/productos/buscar")
    suspend fun buscarProductos(@Query("nombre") nombre: String): Response<List<ProductoDto>>
    
    @GET("api/productos/{id}")
    suspend fun getProducto(@Path("id") id: Long): Response<ProductoDto>
    
    @POST("api/productos")
    suspend fun createProducto(
        @Body producto: ProductoDto,
        @Header("Authorization") token: String
    ): Response<ProductoDto>
    
    @PATCH("api/productos/{id}/stock")
    suspend fun updateStock(
        @Path("id") id: Long,
        @Query("cantidad") cantidad: Int,
        @Header("Authorization") token: String
    ): Response<Map<String, String>>
}
```

---

## 🐳 INICIAR BACKEND CON DOCKER

### En PowerShell:

```powershell
# Navegar a la carpeta del backend
cd "C:\Users\Asgard\Front_level_up\Entrega EVA3"

# Iniciar todos los servicios
docker-compose up -d --build

# Verificar que todo esté corriendo
docker-compose ps

# Ver logs si hay problemas
docker-compose logs -f
```

### Servicios esperados:
- ✅ postgres (5432)
- ✅ api-gateway (8080)
- ✅ auth-service (8081)
- ✅ user-service (8082)
- ✅ product-service (8083)
- ✅ order-service (8084)

---

## ✅ TESTING RÁPIDO

### 1. Probar que el backend responde:

```powershell
# Test Auth Service
curl http://localhost:8081/api/auth/health

# Test Product Service
curl http://localhost:8083/api/productos/health

# Test endpoints reales
curl http://localhost:8083/api/productos
```

### 2. Desde tu app Android:

```kotlin
// En algún ViewModel
viewModelScope.launch {
    try {
        val response = RetrofitClient.apiService.getProductos()
        if (response.isSuccessful) {
            val productos = response.body()
            Log.d("PRODUCTOS", "Recibidos: ${productos?.size}")
        } else {
            Log.e("ERROR", "Code: ${response.code()}")
        }
    } catch (e: Exception) {
        Log.e("ERROR", "Exception: ${e.message}")
    }
}
```

---

## 📝 CREDENCIALES DE PRUEBA

Según tu frontend React:
- **Admin:** admin@levelup.com / admin123
- **Usuario:** usuario@ejemplo.com / usuario123

---

## 🔥 PRÓXIMOS PASOS

1. ✅ Iniciar Docker backend
2. ✅ Actualizar ApiConfig.kt con URLs correctas
3. ✅ Actualizar DTOs según tu backend
4. ✅ Probar login desde la app
5. ✅ Probar carga de productos
6. ✅ Implementar token storage (DataStore)
7. ✅ Integrar con ViewModels existentes

---

**¿LISTO PARA EMPEZAR?** 🚀
