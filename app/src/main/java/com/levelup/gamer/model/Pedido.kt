package com.levelup.gamer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val numeroPedido: String,
    val userId: Int,
    val items: String, // JSON string de los items
    val total: Double,
    val fecha: Long = System.currentTimeMillis(),
    val estado: EstadoPedido = EstadoPedido.PROCESANDO,
    val tiempoEstimadoEntrega: String = "45-60 minutos"
) {
    fun getFechaFormateada(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(fecha))
    }
}

enum class EstadoPedido(val descripcion: String, val orden: Int) {
    PROCESANDO("Procesando pedido", 0),
    PREPARANDO("Preparando pedido", 1),
    EN_CAMINO("En camino", 2),
    ENTREGADO("Entregado", 3);
    
    fun siguiente(): EstadoPedido? {
        return values().find { it.orden == this.orden + 1 }
    }
    
    fun getIcono(): String {
        return when (this) {
            PROCESANDO -> "⏳"
            PREPARANDO -> "📦"
            EN_CAMINO -> "🚚"
            ENTREGADO -> "✅"
        }
    }
    
    fun getMensaje(): String {
        return when (this) {
            PROCESANDO -> "Estamos procesando tu pedido..."
            PREPARANDO -> "Tu pedido está siendo preparado"
            EN_CAMINO -> "Tu pedido está en camino"
            ENTREGADO -> "¡Tu pedido ha sido entregado!"
        }
    }
}
