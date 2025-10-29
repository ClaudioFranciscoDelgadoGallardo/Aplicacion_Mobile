package com.levelup.gamer.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.levelup.gamer.model.CarritoItem
import com.levelup.gamer.repository.carrito.CarritoDao

/**
 * Base de datos principal de la aplicación usando Room
 * 
 * Room es una biblioteca de persistencia que proporciona una capa
 * de abstracción sobre SQLite para acceso fluido a la base de datos.
 * 
 * @property entities Lista de entidades (tablas) en la base de datos
 * @property version Versión de la base de datos (incrementar cuando hay cambios en el esquema)
 */
@Database(
    entities = [CarritoItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    /**
     * Proporciona acceso al DAO del carrito
     */
    abstract fun carritoDao(): CarritoDao
    
    companion object {
        /**
         * Instancia singleton de la base de datos
         * @Volatile asegura que los cambios sean visibles para todos los threads
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        /**
         * Obtiene la instancia de la base de datos
         * Usa el patrón Singleton con doble verificación de bloqueo
         * 
         * @param context Contexto de la aplicación
         * @return Instancia única de AppDatabase
         */
        fun getDatabase(context: Context): AppDatabase {
            // Si la instancia ya existe, la devuelve
            return INSTANCE ?: synchronized(this) {
                // Doble verificación dentro del bloque sincronizado
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "levelup_gamer_database"
                )
                    // Estrategia de migración: destruir y recrear en desarrollo
                    // En producción, deberías usar migraciones apropiadas
                    .fallbackToDestructiveMigration()
                    .build()
                
                INSTANCE = instance
                instance
            }
        }
    }
}
