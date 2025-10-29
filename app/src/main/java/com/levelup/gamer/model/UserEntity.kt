package com.levelup.gamer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val email: String,
    val password: String,
    val displayName: String,
    val isAdmin: Boolean = false
)
