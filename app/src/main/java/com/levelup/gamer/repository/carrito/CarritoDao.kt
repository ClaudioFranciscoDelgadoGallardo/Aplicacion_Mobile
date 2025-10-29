package com.levelup.gamer.repository.carrito

import androidx.room.*
import com.levelup.gamer.model.CarritoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    
    @Query("SELECT * FROM carrito_items ORDER BY id DESC")
    fun obtenerTodosLosItems(): Flow<List<CarritoItem>>
    
    @Query("SELECT * FROM carrito_items WHERE codigoProducto = :codigoProducto LIMIT 1")
    suspend fun obtenerItemPorCodigo(codigoProducto: String): CarritoItem?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarItem(item: CarritoItem): Long
    
    @Update
    suspend fun actualizarItem(item: CarritoItem)
    
    @Delete
    suspend fun eliminarItem(item: CarritoItem)
    
    @Query("DELETE FROM carrito_items")
    suspend fun eliminarTodosLosItems()
    
    @Query("SELECT COUNT(*) FROM carrito_items")
    fun contarItems(): Flow<Int>
    
    @Query("SELECT SUM(precioNumerico * cantidad) FROM carrito_items")
    fun calcularTotal(): Flow<Double?>
}
