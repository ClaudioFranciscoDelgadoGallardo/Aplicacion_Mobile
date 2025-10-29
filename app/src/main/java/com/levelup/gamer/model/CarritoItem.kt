package com.levelup.gamer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de base de datos para items del carrito de compras
 * 
 * Esta clase representa un producto añadido al carrito.
 * Se almacena en la base de datos Room para persistencia.
 * 
 * @property id ID autogenerado para la base de datos
 * @property codigoProducto Código único del producto
 * @property nombre Nombre del producto
 * @property precio Precio unitario en formato texto
 * @property precioNumerico Precio en formato numérico para cálculos
 * @property cantidad Cantidad de unidades en el carrito
 * @property imagenUrl URL o nombre del recurso de imagen
 */
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
    /**
     * Calcula el subtotal de este item
     * @return Precio total (precio unitario * cantidad)
     */
    fun subtotal(): Double = precioNumerico * cantidad
}
