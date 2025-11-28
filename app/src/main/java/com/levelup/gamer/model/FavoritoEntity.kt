package com.levelup.gamer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favoritos",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId", "productoId"], unique = true)]
)
data class FavoritoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val productoId: Int,
    val productoNombre: String,
    val productoPrecio: Double,
    val productoImagenUrl: String,
    val fechaAgregado: Long = System.currentTimeMillis()
)
