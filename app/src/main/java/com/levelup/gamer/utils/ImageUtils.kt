package com.levelup.gamer.utils

import android.content.Context
import android.util.Log
import com.levelup.gamer.R

object ImageUtils {
    fun getDrawableResourceId(context: Context, imageName: String): Int {
        
        val normalizedName = imageName.lowercase().trim()
        Log.d("ImageUtils", "Buscando imagen: '$imageName' -> normalizado: '$normalizedName'")
        
        val resourceId = when (normalizedName) {
            "ps5" -> R.drawable.ps5
            "xbox_series_x" -> R.drawable.xbox_series_x
            "batterfield6" -> R.drawable.batterfield6
            "diablo_v" -> R.drawable.diablo_v
            "stella_blade" -> R.drawable.stella_blade
            "audifonos" -> R.drawable.audifonos
            "teclado" -> R.drawable.teclado
            "mouse" -> R.drawable.mouse
            "rtx5090" -> R.drawable.rtx5090
            "intel_core" -> R.drawable.intel_core
            "viewsonic" -> R.drawable.viewsonic
            "icono" -> R.drawable.icono
            else -> {
                
                try {
                    val id = context.resources.getIdentifier(
                        normalizedName,
                        "drawable",
                        context.packageName
                    )
                    Log.d("ImageUtils", "Recurso no encontrado en when, usando getIdentifier: $id")
                    id
                } catch (e: Exception) {
                    Log.e("ImageUtils", "Error buscando recurso: ${e.message}")
                    0
                }
            }
        }
        
        Log.d("ImageUtils", "Recurso ID para '$normalizedName': $resourceId")
        return resourceId
    }
}
