package com.levelup.gamer.repository.carrito

import com.levelup.gamer.model.CarritoItem
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio del Carrito de Compras
 * 
 * Proporciona una API limpia para acceder y manipular
 * los items del carrito. Abstrae los detalles de Room Database.
 * 
 * @property carritoDao DAO de Room para operaciones de base de datos
 */
class CarritoRepository(private val carritoDao: CarritoDao) {
    
    /**
     * Flow que emite la lista de items del carrito
     * Se actualiza automáticamente cuando hay cambios
     */
    val itemsCarrito: Flow<List<CarritoItem>> = carritoDao.obtenerTodosLosItems()
    
    /**
     * Flow que emite el número de items en el carrito
     */
    val cantidadItems: Flow<Int> = carritoDao.contarItems()
    
    /**
     * Flow que emite el total del carrito
     */
    val totalCarrito: Flow<Double?> = carritoDao.calcularTotal()
    
    /**
     * Añade un producto al carrito
     * Si ya existe, incrementa la cantidad
     * 
     * @param producto Producto a añadir
     */
    suspend fun agregarProducto(producto: Producto) {
        val itemExistente = carritoDao.obtenerItemPorCodigo(producto.codigo)
        
        if (itemExistente != null) {
            // Si ya existe, incrementa la cantidad
            val itemActualizado = itemExistente.copy(
                cantidad = itemExistente.cantidad + 1
            )
            carritoDao.actualizarItem(itemActualizado)
        } else {
            // Si no existe, crea un nuevo item
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
    
    /**
     * Incrementa la cantidad de un item en el carrito
     * 
     * @param item Item a incrementar
     */
    suspend fun incrementarCantidad(item: CarritoItem) {
        val itemActualizado = item.copy(cantidad = item.cantidad + 1)
        carritoDao.actualizarItem(itemActualizado)
    }
    
    /**
     * Decrementa la cantidad de un item en el carrito
     * Si la cantidad llega a 0, elimina el item
     * 
     * @param item Item a decrementar
     */
    suspend fun decrementarCantidad(item: CarritoItem) {
        if (item.cantidad > 1) {
            val itemActualizado = item.copy(cantidad = item.cantidad - 1)
            carritoDao.actualizarItem(itemActualizado)
        } else {
            eliminarItem(item)
        }
    }
    
    /**
     * Elimina un item del carrito
     * 
     * @param item Item a eliminar
     */
    suspend fun eliminarItem(item: CarritoItem) {
        carritoDao.eliminarItem(item)
    }
    
    /**
     * Vacía completamente el carrito
     */
    suspend fun vaciarCarrito() {
        carritoDao.eliminarTodosLosItems()
    }
    
    /**
     * Extrae el valor numérico de un string de precio
     * Ejemplo: "$45.990" -> 45990.0
     * 
     * @param precioString String con el precio (formato: "$XX.XXX")
     * @return Valor numérico del precio
     */
    private fun extraerPrecioNumerico(precioString: String): Double {
        return try {
            // Elimina el símbolo $ y los puntos de miles, luego convierte a Double
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
    
    /**
     * Formatea un número como precio
     * Ejemplo: 45990.0 -> "$45.990"
     * 
     * @param precio Valor numérico del precio
     * @return String formateado
     */
    fun formatearPrecio(precio: Double): String {
        val precioInt = precio.toInt()
        return "$${"%,d".format(precioInt).replace(",", ".")}"
    }
}
