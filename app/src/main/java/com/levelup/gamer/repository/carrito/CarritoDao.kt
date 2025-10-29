package com.levelup.gamer.repository.carrito

import androidx.room.*
import com.levelup.gamer.model.CarritoItem
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) para el carrito de compras
 * 
 * Define las operaciones de base de datos para los items del carrito.
 * Room genera automáticamente la implementación de estas funciones.
 */
@Dao
interface CarritoDao {
    
    /**
     * Obtiene todos los items del carrito como Flow
     * Flow emite automáticamente cuando hay cambios en la base de datos
     */
    @Query("SELECT * FROM carrito_items ORDER BY id DESC")
    fun obtenerTodosLosItems(): Flow<List<CarritoItem>>
    
    /**
     * Obtiene un item específico por código de producto
     */
    @Query("SELECT * FROM carrito_items WHERE codigoProducto = :codigoProducto LIMIT 1")
    suspend fun obtenerItemPorCodigo(codigoProducto: String): CarritoItem?
    
    /**
     * Inserta un nuevo item en el carrito
     * Si ya existe (mismo id), lo reemplaza
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarItem(item: CarritoItem): Long
    
    /**
     * Actualiza un item existente
     */
    @Update
    suspend fun actualizarItem(item: CarritoItem)
    
    /**
     * Elimina un item del carrito
     */
    @Delete
    suspend fun eliminarItem(item: CarritoItem)
    
    /**
     * Elimina todos los items del carrito
     */
    @Query("DELETE FROM carrito_items")
    suspend fun eliminarTodosLosItems()
    
    /**
     * Cuenta el número total de items en el carrito
     */
    @Query("SELECT COUNT(*) FROM carrito_items")
    fun contarItems(): Flow<Int>
    
    /**
     * Calcula el total del carrito
     */
    @Query("SELECT SUM(precioNumerico * cantidad) FROM carrito_items")
    fun calcularTotal(): Flow<Double?>
}
