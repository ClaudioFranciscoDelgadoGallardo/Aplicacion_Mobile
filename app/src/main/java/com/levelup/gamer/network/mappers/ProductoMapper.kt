package com.levelup.gamer.network.mappers

import com.levelup.gamer.model.Producto
import com.levelup.gamer.network.models.ProductoDto

/**
 * Convierte ProductoDto del backend a Producto del modelo de la app
 */
fun ProductoDto.toProducto(): Producto {
    return Producto(
        codigo = this.codigo,
        nombre = this.nombre,
        precio = this.precio.toString(),
        descripcionCorta = this.descripcion.take(100), // Primeros 100 caracteres
        descripcionLarga = this.descripcion,
        categoria = this.categoria,
        stock = this.stock.toString(),
        especificaciones = listOf(), // Backend no tiene especificaciones, lista vacía
        puntuacion = "4.5", // Valor por defecto
        comentarios = listOf(), // Backend no tiene comentarios, lista vacía
        imagenUrl = this.imagen ?: "icono" // Usa imagen del backend o "icono" por defecto
    )
}

/**
 * Convierte lista de ProductoDto a lista de Producto
 */
fun List<ProductoDto>.toProductos(): List<Producto> {
    return this.map { it.toProducto() }
}
