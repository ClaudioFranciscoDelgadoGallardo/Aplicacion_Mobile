package com.levelup.gamer.network.models

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
    val token: String,
    
    @SerializedName("usuario")
    val usuario: UsuarioDto
)

data class UsuarioDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("correo")
    val correo: String,
    
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("run")
    val run: String? = null,
    
    @SerializedName("rol")
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

data class ProductoDto(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("codigo")
    val codigo: String,
    
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("descripcion")
    val descripcion: String,
    
    @SerializedName("precioVenta")
    val precioVenta: Double,
    
    @SerializedName("stockActual")
    val stockActual: Int,
    
    @SerializedName("categoriaId")
    val categoriaId: Long,
    
    @SerializedName("imagenUrl")
    val imagenUrl: String? = null,
    
    @SerializedName("destacado")
    val destacado: Boolean = false,
    
    @SerializedName("activo")
    val activo: Boolean = true
)

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

data class UpdateStockRequest(
    @SerializedName("productoId")
    val productoId: Long,
    
    @SerializedName("nuevoStock")
    val nuevoStock: Int
)

data class CreateOrdenRequest(
    @SerializedName("usuarioId")
    val usuarioId: Long,
    
    @SerializedName("items")
    val items: List<OrdenItemDto>,
    
    @SerializedName("metodoPago")
    val metodoPago: String = "EFECTIVO"
)
