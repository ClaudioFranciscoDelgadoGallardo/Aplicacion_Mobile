package com.levelup.gamer.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val password: String,
    val nombre: String,
    val isAdmin: Boolean = false,
    val descuentoPorcentaje: Int = 0,
    val puntos: Int = 0,
    val totalCompras: Int = 0
) {
    fun tieneDescuento(): Boolean = descuentoPorcentaje > 0
    
    fun calcularPrecioConDescuento(precio: Double): Double {
        return if (descuentoPorcentaje > 0) {
            precio * (1 - descuentoPorcentaje / 100.0)
        } else {
            precio
        }
    }
    
    companion object {
        fun calcularDescuentoPorEmail(email: String): Int {
            return if (email.lowercase().endsWith("@duocuc.cl")) {
                20 // 20% de descuento para DuocUC
            } else {
                0
            }
        }
    }
}
