package com.levelup.gamer.utils

import android.content.Context
import com.levelup.gamer.R

object ImageUtils {
    fun getDrawableResourceId(context: Context, imageName: String): Int {
        return when (imageName) {
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
            else -> 0
        }
    }
}
