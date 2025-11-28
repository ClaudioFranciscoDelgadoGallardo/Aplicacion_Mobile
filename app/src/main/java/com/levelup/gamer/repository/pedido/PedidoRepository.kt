package com.levelup.gamer.repository.pedido

import com.levelup.gamer.model.Pedido
import com.levelup.gamer.model.EstadoPedido
import com.levelup.gamer.repository.auth.UserDao
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class PedidoRepository(
    private val pedidoDao: PedidoDao,
    private val userDao: UserDao? = null
) {
    
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
        
        // Calcular puntos ganados (5% del total)
        val puntosGanados = (total * 0.05).roundToInt()
        
        val pedido = Pedido(
            numeroPedido = numeroPedido,
            userId = userId,
            items = items,
            total = total,
            estado = EstadoPedido.PROCESANDO,
            puntosGanados = puntosGanados
        )
        
        val pedidoId = pedidoDao.insertarPedido(pedido)
        
        // Actualizar puntos y total de compras del usuario
        userDao?.let { dao ->
            val user = dao.getUserById(userId)
            user?.let {
                val updatedUser = it.copy(
                    puntos = it.puntos + puntosGanados,
                    totalCompras = it.totalCompras + 1
                )
                dao.updateUser(updatedUser)
            }
        }
        
        return pedidoId
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
