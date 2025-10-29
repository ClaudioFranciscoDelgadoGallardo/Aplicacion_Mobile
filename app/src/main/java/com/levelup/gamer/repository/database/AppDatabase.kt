package com.levelup.gamer.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.levelup.gamer.model.CarritoItem
import com.levelup.gamer.repository.carrito.CarritoDao

@Database(
    entities = [CarritoItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun carritoDao(): CarritoDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "levelup_gamer_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                
                INSTANCE = instance
                instance
            }
        }
    }
}
