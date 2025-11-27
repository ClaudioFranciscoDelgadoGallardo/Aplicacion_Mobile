package com.levelup.gamer.repository.pedido

import com.levelup.gamer.model.Pedido
import com.levelup.gamer.model.EstadoPedido
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class PedidoRepository(private val pedidoDao: PedidoDao) {
    
    fun obtenerTodosPedidos(): Flow<List<Pedido>> {
        return pedidoDao.obtenerTodosPedidos()
    }
    
    fun obtenerPedidosPorUsuario(userId: Int): Flow<List<Pedido>> {
        return pedidoDao.obtenerPedidosPorUsuario(userId)
    }
    
    suspend fun obtenerPedidoPorId(pedidoId: Int): Pedido? {
        return pedidoDao.obtenerPedidoPorId(pedidoId)
    }
    
    suspend fun crearPedido(userId: Int, items: String, total: Double): Long {
        val numeroPedido = generarNumeroPedido()
        val pedido = Pedido(
            numeroPedido = numeroPedido,
            userId = userId,
            items = items,
            total = total,
            estado = EstadoPedido.PROCESANDO
        )
        return pedidoDao.insertarPedido(pedido)
    }
    
    suspend fun actualizarEstadoPedido(pedido: Pedido, nuevoEstado: EstadoPedido) {
        val pedidoActualizado = pedido.copy(estado = nuevoEstado)
        pedidoDao.actualizarPedido(pedidoActualizado)
    }
    
    private fun generarNumeroPedido(): String {
        val timestamp = System.currentTimeMillis()
        val random = (1000..9999).random()
        return "ORD-${timestamp.toString().takeLast(6)}-$random"
    }
}
