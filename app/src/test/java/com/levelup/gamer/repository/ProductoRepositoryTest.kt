package com.levelup.gamer.repository

import com.levelup.gamer.model.Producto
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ProductoRepositoryTest {

    private lateinit var repository: ProductoRepository

    @Before
    fun setup() {
        repository = ProductoRepository()
    }

    @Test
    fun `obtenerTodosLosProductos retorna lista no vacia`() {
        val productos = repository.obtenerTodosLosProductos()
        assertNotNull(productos)
        assertTrue(productos.isNotEmpty())
    }

    @Test
    fun `obtenerTodosLosProductos retorna 11 productos`() {
        val productos = repository.obtenerTodosLosProductos()
        assertEquals(11, productos.size)
    }

    @Test
    fun `obtenerProductosDestacados retorna maximo 6 productos`() {
        val destacados = repository.obtenerProductosDestacados()
        assertNotNull(destacados)
        assertTrue(destacados.size <= 6)
    }

    @Test
    fun `obtenerProductosDestacados retorna productos validos`() {
        val destacados = repository.obtenerProductosDestacados()
        destacados.forEach { producto ->
            assertNotNull(producto.codigo)
            assertNotNull(producto.nombre)
            assertNotNull(producto.precio)
        }
    }

    @Test
    fun `buscarProductos con query vacio retorna todos los productos`() {
        val resultados = repository.buscarProductos("")
        assertEquals(repository.obtenerTodosLosProductos().size, resultados.size)
    }

    @Test
    fun `buscarProductos con query en blanco retorna todos los productos`() {
        val resultados = repository.buscarProductos("   ")
        assertEquals(repository.obtenerTodosLosProductos().size, resultados.size)
    }

    @Test
    fun `buscarProductos con PlayStation retorna PlayStation 5`() {
        val resultados = repository.buscarProductos("PlayStation")
        assertTrue(resultados.isNotEmpty())
        assertTrue(resultados.any { it.nombre.contains("PlayStation", ignoreCase = true) })
    }

    @Test
    fun `buscarProductos con xbox retorna Xbox Series X`() {
        val resultados = repository.buscarProductos("xbox")
        assertTrue(resultados.isNotEmpty())
        assertTrue(resultados.any { it.nombre.contains("Xbox", ignoreCase = true) })
    }

    @Test
    fun `buscarProductos con consola retorna productos de categoria Consolas`() {
        val resultados = repository.buscarProductos("consola")
        assertTrue(resultados.isNotEmpty())
        assertTrue(resultados.any { it.categoria == "Consolas" })
    }

    @Test
    fun `buscarProductos es case insensitive`() {
        val resultadosLower = repository.buscarProductos("playstation")
        val resultadosUpper = repository.buscarProductos("PLAYSTATION")
        val resultadosMixed = repository.buscarProductos("PlAyStAtIoN")

        assertEquals(resultadosLower.size, resultadosUpper.size)
        assertEquals(resultadosLower.size, resultadosMixed.size)
    }

    @Test
    fun `buscarProductos con texto inexistente retorna lista vacia`() {
        val resultados = repository.buscarProductos("ProductoInexistente12345")
        assertTrue(resultados.isEmpty())
    }

    @Test
    fun `obtenerProductosPorCategoria con Consolas retorna solo consolas`() {
        val consolas = repository.obtenerProductosPorCategoria("Consolas")
        assertTrue(consolas.isNotEmpty())
        consolas.forEach { producto ->
            assertEquals("Consolas", producto.categoria)
        }
    }

    @Test
    fun `obtenerProductosPorCategoria con Juegos retorna solo juegos`() {
        val juegos = repository.obtenerProductosPorCategoria("Juegos")
        assertTrue(juegos.isNotEmpty())
        juegos.forEach { producto ->
            assertEquals("Juegos", producto.categoria)
        }
    }

    @Test
    fun `obtenerProductosPorCategoria con Accesorios retorna solo accesorios`() {
        val accesorios = repository.obtenerProductosPorCategoria("Accesorios")
        assertTrue(accesorios.isNotEmpty())
        accesorios.forEach { producto ->
            assertEquals("Accesorios", producto.categoria)
        }
    }

    @Test
    fun `obtenerProductosPorCategoria con PC Gaming retorna solo productos PC`() {
        val pcGaming = repository.obtenerProductosPorCategoria("PC Gaming")
        assertTrue(pcGaming.isNotEmpty())
        pcGaming.forEach { producto ->
            assertEquals("PC Gaming", producto.categoria)
        }
    }

    @Test
    fun `obtenerProductosPorCategoria con categoria inexistente retorna lista vacia`() {
        val resultados = repository.obtenerProductosPorCategoria("CategoriaInexistente")
        assertTrue(resultados.isEmpty())
    }

    @Test
    fun `obtenerProductoPorCodigo con CONS-001 retorna PlayStation 5`() {
        val producto = repository.obtenerProductoPorCodigo("CONS-001")
        assertNotNull(producto)
        assertEquals("PlayStation 5", producto?.nombre)
        assertEquals("Consolas", producto?.categoria)
    }

    @Test
    fun `obtenerProductoPorCodigo con PC-001 retorna RTX 5090`() {
        val producto = repository.obtenerProductoPorCodigo("PC-001")
        assertNotNull(producto)
        assertEquals("NVIDIA GeForce RTX 5090", producto?.nombre)
    }

    @Test
    fun `obtenerProductoPorCodigo con codigo inexistente retorna null`() {
        val producto = repository.obtenerProductoPorCodigo("INEXISTENTE-999")
        assertNull(producto)
    }

    @Test
    fun `obtenerProductoPorCodigo es case sensitive`() {
        val producto = repository.obtenerProductoPorCodigo("cons-001")
        assertNull(producto)
    }

    @Test
    fun `obtenerCategorias retorna lista no vacia`() {
        val categorias = repository.obtenerCategorias()
        assertNotNull(categorias)
        assertTrue(categorias.isNotEmpty())
    }

    @Test
    fun `obtenerCategorias retorna 4 categorias`() {
        val categorias = repository.obtenerCategorias()
        assertEquals(4, categorias.size)
    }

    @Test
    fun `obtenerCategorias incluye Consolas`() {
        val categorias = repository.obtenerCategorias()
        assertTrue(categorias.contains("Consolas"))
    }

    @Test
    fun `obtenerCategorias incluye Juegos`() {
        val categorias = repository.obtenerCategorias()
        assertTrue(categorias.contains("Juegos"))
    }

    @Test
    fun `obtenerCategorias incluye Accesorios`() {
        val categorias = repository.obtenerCategorias()
        assertTrue(categorias.contains("Accesorios"))
    }

    @Test
    fun `obtenerCategorias incluye PC Gaming`() {
        val categorias = repository.obtenerCategorias()
        assertTrue(categorias.contains("PC Gaming"))
    }

    @Test
    fun `obtenerCategorias retorna categorias sin duplicados`() {
        val categorias = repository.obtenerCategorias()
        val categoriasUnicas = categorias.distinct()
        assertEquals(categorias.size, categoriasUnicas.size)
    }

    @Test
    fun `obtenerCategorias retorna categorias ordenadas alfabeticamente`() {
        val categorias = repository.obtenerCategorias()
        val categoriasOrdenadas = categorias.sorted()
        assertEquals(categoriasOrdenadas, categorias)
    }

    @Test
    fun `todos los productos tienen codigo unico`() {
        val productos = repository.obtenerTodosLosProductos()
        val codigos = productos.map { it.codigo }
        val codigosUnicos = codigos.distinct()
        assertEquals(productos.size, codigosUnicos.size)
    }

    @Test
    fun `todos los productos tienen nombre no vacio`() {
        val productos = repository.obtenerTodosLosProductos()
        productos.forEach { producto ->
            assertTrue(producto.nombre.isNotBlank())
        }
    }

    @Test
    fun `todos los productos tienen precio valido`() {
        val productos = repository.obtenerTodosLosProductos()
        productos.forEach { producto ->
            assertTrue(producto.precio.isNotBlank())
            assertTrue(producto.precio.startsWith("$"))
        }
    }

    @Test
    fun `todos los productos tienen stock numerico`() {
        val productos = repository.obtenerTodosLosProductos()
        productos.forEach { producto ->
            assertTrue(producto.stock.isNotBlank())
            assertTrue(producto.stock.toIntOrNull() != null)
            assertTrue(producto.stock.toInt() >= 0)
        }
    }

    @Test
    fun `todos los productos tienen puntuacion valida`() {
        val productos = repository.obtenerTodosLosProductos()
        productos.forEach { producto ->
            val puntuacion = producto.puntuacion.toDoubleOrNull()
            assertNotNull(puntuacion)
            assertTrue(puntuacion!! >= 0.0)
            assertTrue(puntuacion <= 5.0)
        }
    }

    @Test
    fun `todos los productos tienen al menos una especificacion`() {
        val productos = repository.obtenerTodosLosProductos()
        productos.forEach { producto ->
            assertTrue(producto.especificaciones.isNotEmpty())
        }
    }

    @Test
    fun `todos los productos tienen descripcion larga`() {
        val productos = repository.obtenerTodosLosProductos()
        productos.forEach { producto ->
            assertTrue(producto.descripcionLarga.isNotBlank())
            assertTrue(producto.descripcionLarga.length > producto.descripcionCorta.length)
        }
    }
}

