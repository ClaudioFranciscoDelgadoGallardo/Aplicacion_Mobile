package com.levelup.gamer.model

import com.google.gson.annotations.SerializedName

/**
 * Modelo de datos para un Producto en la tienda gamer
 * 
 * @property codigo Código único del producto
 * @property nombre Nombre del producto
 * @property precio Precio en formato de texto (ej: "$45.990")
 * @property descripcionCorta Descripción breve para tarjetas
 * @property descripcionLarga Descripción detallada del producto
 * @property categoria Categoría del producto (Consolas, Juegos, Accesorios, PC Gaming)
 * @property stock Cantidad disponible en texto
 * @property especificaciones Lista de especificaciones técnicas
 * @property puntuacion Puntuación del producto (ej: "4.5")
 * @property comentarios Lista de comentarios de usuarios
 * @property imagenUrl URL o nombre del recurso de imagen
 */
data class Producto(
    @SerializedName("Código") val codigo: String,
    @SerializedName("Nombre") val nombre: String,
    @SerializedName("Precio") val precio: String,
    @SerializedName("Descripción Corta") val descripcionCorta: String,
    @SerializedName("Descripción Larga") val descripcionLarga: String,
    @SerializedName("Categoría") val categoria: String,
    @SerializedName("Stock") val stock: String,
    @SerializedName("Especificaciones") val especificaciones: List<String>,
    @SerializedName("Puntuacion") val puntuacion: String,
    @SerializedName("Comentarios") val comentarios: List<String>,
    @SerializedName("imgLink") val imagenUrl: String
)

/**
 * Respuesta JSON que contiene la lista de productos
 */
data class ProductoJsonResponse(
    val productos: List<Producto>
)
