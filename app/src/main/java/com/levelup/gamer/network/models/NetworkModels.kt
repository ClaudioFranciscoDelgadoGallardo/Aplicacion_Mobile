package com.levelup.gamer.network.models

import com.google.gson.annotations.SerializedName

// ==================== AUTENTICACIÓN ====================

// Request para Login (según tu backend)
data class LoginRequest(
    @SerializedName("correo")
    val correo: String,
    
    @SerializedName("password")
    val password: String
)

// Request para Registro (según tu backend)
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

// Response de Auth (tu backend devuelve esto)
data class AuthResponse(
    @SerializedName("token")
    val token: String,
    
    @SerializedName("usuario")
    val usuario: UsuarioDto
)

// DTO de Usuario (adaptado a tu backend)
data class UsuarioDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("correo")  // Backend usa "correo" en lugar de "email"
    val correo: String,
    
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("run")  // Campo adicional del backend
    val run: String? = null,
    
    @SerializedName("rol")  // Backend devuelve rol (ADMIN, USUARIO, etc.)
    val rol: String? = null,
    
    @SerializedName("isAdmin")
    val isAdmin: Boolean = false,
    
    @SerializedName("descuentoPorcentaje")
    val descuentoPorcentaje: Int = 0,
    
    @SerializedName("puntos")
    val puntos: Int = 0,
    
    @SerializedName("totalCompras")
    val totalCompras: Int = 0,
    
    @SerializedName("telefono")
    val telefono: String? = null,
    
    @SerializedName("direccion")
    val direccion: String? = null
)

// DTO de Producto (adaptado a tu backend)
data class ProductoDto(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("codigo")
    val codigo: String,
    
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("descripcion")  // Backend usa "descripcion" simple
    val descripcion: String,
    
    @SerializedName("precio")
    val precio: Double,
    
    @SerializedName("stock")
    val stock: Int,
    
    @SerializedName("categoria")
    val categoria: String,
    
    @SerializedName("imagen")  // Backend usa "imagen"
    val imagen: String? = null,
    
    @SerializedName("destacado")  // Campo del backend
    val destacado: Boolean = false,
    
    @SerializedName("activo")
    val activo: Boolean = true
)

// DTO de Orden/Pedido
data class OrdenDto(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("usuarioId")
    val usuarioId: Long,
    
    @SerializedName("items")
    val items: List<OrdenItemDto>,
    
    @SerializedName("total")
    val total: Double,
    
    @SerializedName("descuento")
    val descuento: Double = 0.0,
    
    @SerializedName("puntosGanados")
    val puntosGanados: Int = 0,
    
    @SerializedName("estado")
    val estado: String = "PENDIENTE",
    
    @SerializedName("fechaCreacion")
    val fechaCreacion: String? = null
)

// Item de una orden
data class OrdenItemDto(
    @SerializedName("productoId")
    val productoId: Long,
    
    @SerializedName("nombreProducto")
    val nombreProducto: String,
    
    @SerializedName("cantidad")
    val cantidad: Int,
    
    @SerializedName("precio")
    val precio: Double,
    
    @SerializedName("subtotal")
    val subtotal: Double
)

// Respuesta genérica de la API
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    
    @SerializedName("data")
    val data: T? = null,
    
    @SerializedName("mensaje")
    val mensaje: String? = null,
    
    @SerializedName("error")
    val error: String? = null
)

// Request para actualizar stock
data class UpdateStockRequest(
    @SerializedName("productoId")
    val productoId: Long,
    
    @SerializedName("nuevoStock")
    val nuevoStock: Int
)

// Request para crear orden
data class CreateOrdenRequest(
    @SerializedName("usuarioId")
    val usuarioId: Long,
    
    @SerializedName("items")
    val items: List<OrdenItemDto>,
    
    @SerializedName("metodoPago")
    val metodoPago: String = "EFECTIVO"
)
