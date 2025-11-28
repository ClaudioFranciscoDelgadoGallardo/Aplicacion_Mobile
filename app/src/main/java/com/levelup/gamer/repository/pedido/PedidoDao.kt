package com.levelup.gamer.repository.pedido

import androidx.room.*
import com.levelup.gamer.model.Pedido
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {
    
    @Query("SELECT * FROM pedidos ORDER BY fecha DESC")
    fun obtenerTodosPedidos(): Flow<List<Pedido>>
    
    @Query("SELECT * FROM pedidos WHERE userId = :userId ORDER BY fecha DESC")
    fun obtenerPedidosPorUsuario(userId: Int): Flow<List<Pedido>>
    
    @Query("SELECT * FROM pedidos WHERE userId = :userId ORDER BY fecha DESC")
    suspend fun getPedidosByUserId(userId: Int): List<Pedido>
    
    @Query("SELECT * FROM pedidos WHERE id = :pedidoId LIMIT 1")
    suspend fun obtenerPedidoPorId(pedidoId: Int): Pedido?
    
    @Insert
    suspend fun insertarPedido(pedido: Pedido): Long
    
    @Update
    suspend fun actualizarPedido(pedido: Pedido)
    
    @Query("DELETE FROM pedidos WHERE id = :pedidoId")
    suspend fun eliminarPedido(pedidoId: Int)
}
