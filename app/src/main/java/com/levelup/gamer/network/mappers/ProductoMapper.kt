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
        precio = this.precioVenta.toString(),
        descripcionCorta = this.descripcion.take(100), // Primeros 100 caracteres
        descripcionLarga = this.descripcion,
        categoria = obtenerNombreCategoria(this.categoriaId),
        stock = this.stockActual.toString(),
        especificaciones = listOf(), // Backend no tiene especificaciones, lista vacía
        puntuacion = "4.5", // Valor por defecto
        comentarios = listOf(), // Backend no tiene comentarios, lista vacía
        imagenUrl = this.imagenPrincipal ?: "icono" // Usa imagen del backend o "icono" por defecto
    )
}

/**
 * Convierte categoriaId a nombre de categoría
 */
private fun obtenerNombreCategoria(categoriaId: Long): String {
    return when (categoriaId) {
        1L -> "Juegos de Mesa"
        2L -> "Accesorios"
        3L -> "Consolas"
        4L -> "Videojuegos"
        5L -> "Merchandising"
        6L -> "PC Gaming"
        else -> "Otros"
    }
}

/**
 * Convierte lista de ProductoDto a lista de Producto
 */
fun List<ProductoDto>.toProductos(): List<Producto> {
    return this.map { it.toProducto() }
}
