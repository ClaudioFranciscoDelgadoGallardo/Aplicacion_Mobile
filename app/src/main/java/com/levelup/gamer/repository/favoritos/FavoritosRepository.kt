package com.levelup.gamer.repository.favoritos

import com.levelup.gamer.model.FavoritoEntity
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritosRepository @Inject constructor(
    private val favoritoDao: FavoritoDao
) {
    
    fun getFavoritosByUser(userId: Int): Flow<List<FavoritoEntity>> {
        return favoritoDao.getFavoritosByUser(userId)
    }
    
    suspend fun getFavoritosCount(userId: Int): Int {
        return favoritoDao.getFavoritosCount(userId)
    }
    
    suspend fun isFavorito(userId: Int, productoId: Int): Boolean {
        return favoritoDao.getFavorito(userId, productoId) != null
    }
    
    suspend fun addFavorito(userId: Int, producto: Producto) {
        val favorito = FavoritoEntity(
            userId = userId,
            productoId = producto.id,
            productoNombre = producto.nombre,
            productoPrecio = producto.precio,
            productoImagenUrl = producto.imagenUrl,
            fechaAgregado = System.currentTimeMillis()
        )
        favoritoDao.insertFavorito(favorito)
    }
    
    suspend fun removeFavorito(userId: Int, productoId: Int) {
        favoritoDao.deleteFavorito(userId, productoId)
    }
    
    suspend fun toggleFavorito(userId: Int, producto: Producto): Boolean {
        val esFavorito = isFavorito(userId, producto.id)
        
        if (esFavorito) {
            removeFavorito(userId, producto.id)
            return false
        } else {
            addFavorito(userId, producto)
            return true
        }
    }
    
    suspend fun removeAllFavoritos(userId: Int) {
        favoritoDao.deleteAllFavoritos(userId)
    }
}
