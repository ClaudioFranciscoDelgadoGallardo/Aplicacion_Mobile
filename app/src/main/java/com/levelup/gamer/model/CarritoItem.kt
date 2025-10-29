package com.levelup.gamer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito_items")
data class CarritoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    val codigoProducto: String,
    val nombre: String,
    val precio: String,
    val precioNumerico: Double,
    val cantidad: Int = 1,
    val imagenUrl: String
) {
    fun subtotal(): Double = precioNumerico * cantidad
}
