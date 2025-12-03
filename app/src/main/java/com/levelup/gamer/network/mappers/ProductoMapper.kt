package com.levelup.gamer.network.mappers

import com.levelup.gamer.model.Producto
import com.levelup.gamer.network.models.ProductoDto

fun ProductoDto.toProducto(): Producto {
    return Producto(
        codigo = this.codigo,
        nombre = this.nombre,
        precio = this.precioVenta.toString(),
        descripcionCorta = this.descripcion.take(100),
        descripcionLarga = this.descripcion,
        categoria = obtenerNombreCategoria(this.categoriaId),
        stock = this.stockActual.toString(),
        especificaciones = listOf(),
        puntuacion = "4.5",
        comentarios = listOf(),
        imagenUrl = this.imagenUrl ?: "icono"
    )
}

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

fun List<ProductoDto>.toProductos(): List<Producto> {
    return this.map { it.toProducto() }
}
