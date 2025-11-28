package com.levelup.gamer.repository.database

import androidx.room.*
import com.levelup.gamer.model.FavoritoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritoDao {
    
    @Query("SELECT * FROM favoritos WHERE userId = :userId ORDER BY fechaAgregado DESC")
    fun getFavoritosByUser(userId: Int): Flow<List<FavoritoEntity>>
    
    @Query("SELECT COUNT(*) FROM favoritos WHERE userId = :userId")
    suspend fun getFavoritosCount(userId: Int): Int
    
    @Query("SELECT * FROM favoritos WHERE userId = :userId AND productoId = :productoId")
    suspend fun getFavorito(userId: Int, productoId: Int): FavoritoEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorito(favorito: FavoritoEntity)
    
    @Query("DELETE FROM favoritos WHERE userId = :userId AND productoId = :productoId")
    suspend fun deleteFavorito(userId: Int, productoId: Int)
    
    @Query("DELETE FROM favoritos WHERE userId = :userId")
    suspend fun deleteAllFavoritos(userId: Int)
}
