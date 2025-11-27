package com.levelup.gamer.network.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ProductoDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("codigo")
    val codigo: String?,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String?,
    @SerializedName("precioBase")
    val precioBase: BigDecimal,
    @SerializedName("precioVenta")
    val precioVenta: BigDecimal,
    @SerializedName("costo")
    val costo: BigDecimal?,
    @SerializedName("categoriaId")
    val categoriaId: Long,
    @SerializedName("stockActual")
    val stockActual: Int,
    @SerializedName("imagenUrl")
    val imagenUrl: String?,
    @SerializedName("destacado")
    val destacado: Boolean,
    @SerializedName("activo")
    val activo: Boolean,
    @SerializedName("marca")
    val marca: String?,
    @SerializedName("descuento")
    val descuento: BigDecimal?,
    @SerializedName("fechaCreacion")
    val fechaCreacion: String?,
    @SerializedName("fechaActualizacion")
    val fechaActualizacion: String?
)
