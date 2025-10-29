package com.levelup.gamer.repository.carrito

import com.levelup.gamer.model.CarritoItem
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.flow.Flow

class CarritoRepository(private val carritoDao: CarritoDao) {
    
    val itemsCarrito: Flow<List<CarritoItem>> = carritoDao.obtenerTodosLosItems()
    
    val cantidadItems: Flow<Int> = carritoDao.contarItems()
    
    val totalCarrito: Flow<Double?> = carritoDao.calcularTotal()
    
    suspend fun agregarProducto(producto: Producto) {
        val itemExistente = carritoDao.obtenerItemPorCodigo(producto.codigo)
        
        if (itemExistente != null) {
            val itemActualizado = itemExistente.copy(
                cantidad = itemExistente.cantidad + 1
            )
            carritoDao.actualizarItem(itemActualizado)
        } else {
            val nuevoItem = CarritoItem(
                codigoProducto = producto.codigo,
                nombre = producto.nombre,
                precio = producto.precio,
                precioNumerico = extraerPrecioNumerico(producto.precio),
                cantidad = 1,
                imagenUrl = producto.imagenUrl
            )
            carritoDao.insertarItem(nuevoItem)
        }
    }
    
    suspend fun incrementarCantidad(item: CarritoItem) {
        val itemActualizado = item.copy(cantidad = item.cantidad + 1)
        carritoDao.actualizarItem(itemActualizado)
    }
    
    suspend fun decrementarCantidad(item: CarritoItem) {
        if (item.cantidad > 1) {
            val itemActualizado = item.copy(cantidad = item.cantidad - 1)
            carritoDao.actualizarItem(itemActualizado)
        } else {
            eliminarItem(item)
        }
    }
    
    suspend fun eliminarItem(item: CarritoItem) {
        carritoDao.eliminarItem(item)
    }
    
    suspend fun vaciarCarrito() {
        carritoDao.eliminarTodosLosItems()
    }
    
    private fun extraerPrecioNumerico(precioString: String): Double {
        return try {
            precioString
                .replace("$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim()
                .toDouble()
        } catch (e: Exception) {
            0.0
        }
    }
    
    fun formatearPrecio(precio: Double): String {
        val precioInt = precio.toInt()
        return "$${"%,d".format(precioInt).replace(",", ".")}"
    }
}
