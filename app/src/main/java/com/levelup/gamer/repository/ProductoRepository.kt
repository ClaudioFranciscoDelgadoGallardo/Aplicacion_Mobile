package com.levelup.gamer.repository

import com.levelup.gamer.model.Producto
import com.levelup.gamer.network.RetrofitClient
import com.levelup.gamer.network.dto.ProductoDto

class ProductoRepository {
    
    private val productoApi = RetrofitClient.productoApi
    
    suspend fun obtenerProductosDestacadosFromBackend(): Result<List<Producto>> {
        return try {
            val response = productoApi.obtenerDestacados()
            if (response.isSuccessful && response.body() != null) {
                val productos = response.body()!!.map { it.toProducto() }
                Result.success(productos)
            } else {
                Result.failure(Exception("Error al obtener productos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun obtenerTodosFromBackend(): Result<List<Producto>> {
        return try {
            val response = productoApi.obtenerTodos()
            if (response.isSuccessful && response.body() != null) {
                val productos = response.body()!!.map { it.toProducto() }
                Result.success(productos)
            } else {
                Result.failure(Exception("Error al obtener productos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarProductosFromBackend(nombre: String): Result<List<Producto>> {
        return try {
            val response = productoApi.buscarPorNombre(nombre)
            if (response.isSuccessful && response.body() != null) {
                val productos = response.body()!!.map { it.toProducto() }
                Result.success(productos)
            } else {
                Result.failure(Exception("Error al buscar productos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun ProductoDto.toProducto(): Producto {
        val precioFormateado = formatearPrecio(this.precioVenta.toDouble())
        return Producto(
            codigo = this.codigo ?: "",
            nombre = this.nombre,
            precio = precioFormateado,
            descripcionCorta = this.descripcion?.take(100) ?: "",
            descripcionLarga = this.descripcion ?: "",
            categoria = when(this.categoriaId) {
                1L -> "Consolas"
                2L -> "Juegos"
                3L -> "Accesorios"
                4L -> "PC Gaming"
                else -> "Otros"
            },
            stock = this.stockActual.toString(),
            especificaciones = emptyList(),
            puntuacion = "4.5",
            comentarios = emptyList(),
            imagenUrl = this.imagenUrl ?: ""
        )
    }
    
    private fun formatearPrecio(precio: Double): String {
        val precioInt = precio.toInt()
        val precioStr = precioInt.toString()
        val length = precioStr.length
        
        return when {
            length > 6 -> {
                val millones = precioStr.substring(0, length - 6)
                val miles = precioStr.substring(length - 6, length - 3)
                val unidades = precioStr.substring(length - 3)
                "$$millones.$miles.$unidades"
            }
            length > 3 -> {
                val miles = precioStr.substring(0, length - 3)
                val unidades = precioStr.substring(length - 3)
                "$$miles.$unidades"
            }
            else -> "$$precioStr"
        }
    }
    
    private val _productosMutables = mutableListOf(
        Producto(
            codigo = "CONS-001",
            nombre = "PlayStation 5",
            precio = "$499.990",
            descripcionCorta = "Consola de nueva generación con tecnología de trazado de rayos",
            descripcionLarga = "PlayStation 5 ofrece una experiencia de juego revolucionaria con gráficos de última generación, tiempos de carga ultra rápidos gracias a su SSD personalizado, y el innovador control DualSense con retroalimentación háptica.",
            categoria = "Consolas",
            stock = "15",
            especificaciones = listOf(
                "CPU: AMD Zen 2 de 8 núcleos",
                "GPU: AMD RDNA 2",
                "RAM: 16GB GDDR6",
                "Almacenamiento: SSD 825GB",
                "Resolución: Hasta 8K"
            ),
            puntuacion = "4.8",
            comentarios = listOf(
                "Increíble experiencia de juego, los tiempos de carga son impresionantes",
                "El DualSense es un cambio revolucionario",
                "Totalmente recomendada para gamers serios"
            ),
            imagenUrl = "ps5"
        ),
        
        Producto(
            codigo = "CONS-002",
            nombre = "Xbox Series X",
            precio = "$479.990",
            descripcionCorta = "La Xbox más potente de la historia con 12 TFLOPS",
            descripcionLarga = "Xbox Series X es la consola Xbox más rápida y potente jamás creada. Con 12 Teraflops de potencia de procesamiento gráfico y tiempos de carga casi instantáneos, experimenta el gaming de nueva generación.",
            categoria = "Consolas",
            stock = "20",
            especificaciones = listOf(
                "CPU: AMD Zen 2 de 8 núcleos a 3.8 GHz",
                "GPU: 12 TFLOPS AMD RDNA 2",
                "RAM: 16GB GDDR6",
                "Almacenamiento: SSD NVMe 1TB",
                "FPS: Hasta 120 FPS"
            ),
            puntuacion = "4.7",
            comentarios = listOf(
                "Potencia pura, corre todos los juegos a 60 FPS",
                "Game Pass es increíble",
                "Diseño elegante y silencioso"
            ),
            imagenUrl = "xbox_series_x"
        ),
        
        Producto(
            codigo = "GAME-001",
            nombre = "Battlefield 6",
            precio = "$57.990",
            descripcionCorta = "Shooter táctico de última generación con combate a gran escala",
            descripcionLarga = "Battlefield 6 lleva la guerra moderna a un nuevo nivel con mapas masivos de 128 jugadores, destrucción avanzada y gráficos de próxima generación. Experimenta batallas épicas con vehículos, infantería y combate táctico intenso.",
            categoria = "Juegos",
            stock = "50",
            especificaciones = listOf(
                "Plataforma: PC, PS5, Xbox Series X",
                "Género: Shooter Táctico",
                "Jugadores: 1-128 online",
                "Editor: Electronic Arts",
                "Clasificación: M17+"
            ),
            puntuacion = "4.7",
            comentarios = listOf(
                "Las batallas a gran escala son increíbles",
                "Gráficos impresionantes y destrucción realista",
                "El mejor Battlefield hasta la fecha"
            ),
            imagenUrl = "batterfield6"
        ),
        
        Producto(
            codigo = "GAME-002",
            nombre = "Diablo IV",
            precio = "$29.990",
            descripcionCorta = "RPG de acción oscuro con combate visceral y mundo compartido",
            descripcionLarga = "Regresa al santuario en esta oscura y brutal aventura. Diablo IV ofrece un vasto mundo abierto, cinco clases únicas, combate dinámico y cooperativo, dungeons generados proceduralmente y la amenaza de Lilith, hija de Mephisto.",
            categoria = "Juegos",
            stock = "40",
            especificaciones = listOf(
                "Plataforma: PS5, Xbox, PC",
                "Género: Action RPG",
                "Jugadores: 1-4 online",
                "Editor: Blizzard Entertainment",
                "Clasificación: M17+"
            ),
            puntuacion = "4.5",
            comentarios = listOf(
                "Addictivo sistema de loot",
                "Atmósfera oscura perfecta",
                "Excelente jugabilidad multijugador"
            ),
            imagenUrl = "diablo_v"
        ),
        
        Producto(
            codigo = "GAME-003",
            nombre = "Stellar Blade",
            precio = "$45.990",
            descripcionCorta = "Action RPG futurista con combate espectacular de estilo hack and slash",
            descripcionLarga = "Una experiencia de acción cinemática ambientada en un futuro distópico. Controla a Eve en su misión para recuperar la Tierra. Combate fluido y desafiante, gráficos impresionantes y una historia épica te esperan en este exclusivo de PS5.",
            categoria = "Juegos",
            stock = "35",
            especificaciones = listOf(
                "Plataforma: PS5",
                "Género: Action RPG",
                "Jugadores: 1",
                "Editor: Sony Interactive",
                "Clasificación: M17+"
            ),
            puntuacion = "4.7",
            comentarios = listOf(
                "Combate increíblemente satisfactorio",
                "Gráficos de nivel AAA",
                "Una de las mejores exclusivas de PS5"
            ),
            imagenUrl = "stella_blade"
        ),
        
        Producto(
            codigo = "ACC-001",
            nombre = "SteelSeries Arctis Nova Pro",
            precio = "$349.990",
            descripcionCorta = "Headset gaming premium con audio Hi-Res y cancelación de ruido activa",
            descripcionLarga = "Auriculares gaming de gama alta con controladores de neodimio de 40mm, audio Hi-Res certificado, cancelación activa de ruido (ANC), micrófono ClearCast Gen 2 retráctil y GameDAC Gen 2 para control total del audio. Compatible con PC, PlayStation y Xbox.",
            categoria = "Accesorios",
            stock = "30",
            especificaciones = listOf(
                "Drivers: 40mm neodimio Hi-Res",
                "ANC: Cancelación activa de ruido",
                "Micrófono: ClearCast Gen 2 retráctil",
                "Conectividad: USB-C, Bluetooth, 3.5mm",
                "Batería: Hasta 44 horas"
            ),
            puntuacion = "4.9",
            comentarios = listOf(
                "Calidad de audio excepcional",
                "ANC funciona perfecto para concentrarse",
                "El mejor headset gaming premium"
            ),
            imagenUrl = "audifonos"
        ),
        
        Producto(
            codigo = "ACC-002",
            nombre = "Corsair K70 RGB PRO",
            precio = "$169.990",
            descripcionCorta = "Teclado mecánico gaming con switches Cherry MX y polling rate de 8000Hz",
            descripcionLarga = "Teclado mecánico de alta performance con switches Cherry MX Red, tecnología AXON con polling rate de 8000Hz para latencia ultra baja, iluminación RGB por tecla personalizable con iCUE, estructura de aluminio y cable USB Type-C trenzado desmontable.",
            categoria = "Accesorios",
            stock = "40",
            especificaciones = listOf(
                "Switches: Cherry MX Red",
                "Polling Rate: 8000Hz",
                "RGB: Per-key RGB con iCUE",
                "Construcción: Aluminio cepillado",
                "Anti-ghosting: 100% con N-Key Rollover"
            ),
            puntuacion = "4.8",
            comentarios = listOf(
                "Polling rate de 8000Hz hace diferencia",
                "Switches Cherry MX súper confiables",
                "Construcción sólida y premium"
            ),
            imagenUrl = "teclado"
        ),
        
        Producto(
            codigo = "ACC-003",
            nombre = "Razer DeathAdder V3 Pro",
            precio = "$149.990",
            descripcionCorta = "Mouse gaming inalámbrico profesional con sensor Focus Pro 30K",
            descripcionLarga = "Mouse gaming inalámbrico ultra liviano (63g) con sensor óptico Focus Pro 30K DPI, switches ópticas Gen-3 para 90 millones de clicks, tecnología HyperSpeed Wireless para latencia de 0.25ms y batería de hasta 90 horas. Diseño ergonómico icónico mejorado.",
            categoria = "Accesorios",
            stock = "35",
            especificaciones = listOf(
                "Sensor: Focus Pro 30K óptico",
                "DPI: Hasta 30,000",
                "Peso: 63g ultraligero",
                "Batería: Hasta 90 horas",
                "Switches: Razer Optical Gen-3"
            ),
            puntuacion = "4.9",
            comentarios = listOf(
                "Increíblemente ligero y preciso",
                "Batería dura semanas",
                "El mejor mouse inalámbrico para FPS"
            ),
            imagenUrl = "mouse"
        ),
        
        Producto(
            codigo = "PC-001",
            nombre = "NVIDIA GeForce RTX 5090",
            precio = "$2.400.000",
            descripcionCorta = "Tarjeta gráfica tope de línea con tecnología Blackwell",
            descripcionLarga = "La GPU más potente jamás creada. RTX 5090 con arquitectura Blackwell ofrece ray tracing revolucionario, DLSS 4 con IA mejorada y rendimiento extremo para gaming en 8K y creación de contenido profesional.",
            categoria = "PC Gaming",
            stock = "5",
            especificaciones = listOf(
                "Arquitectura: Blackwell",
                "Núcleos CUDA: 21,760",
                "VRAM: 32GB GDDR7",
                "Boost Clock: 2.9 GHz",
                "TDP: 600W"
            ),
            puntuacion = "5.0",
            comentarios = listOf(
                "Rendimiento absolutamente brutal",
                "Gaming en 8K sin problemas",
                "La mejor GPU del mercado"
            ),
            imagenUrl = "rtx5090"
        ),
        
        Producto(
            codigo = "PC-002",
            nombre = "Intel Core i9-14900K",
            precio = "$689.990",
            descripcionCorta = "Procesador de 24 núcleos desbloqueado para gaming extremo",
            descripcionLarga = "Procesador Intel de 14ª generación con 24 núcleos (8 P-cores + 16 E-cores) y 32 hilos, frecuencias de hasta 6.0 GHz con Intel Thermal Velocity Boost, soporte DDR5-5600 y overclocking desbloqueado. Rendimiento líder para gaming competitivo y multitarea.",
            categoria = "PC Gaming",
            stock = "22",
            especificaciones = listOf(
                "Núcleos/Hilos: 24/32 (8P+16E)",
                "Frecuencia Base: 3.0 GHz (P-cores)",
                "Frecuencia Turbo: Hasta 6.0 GHz",
                "Caché: 36MB Intel Smart Cache",
                "TDP: 125W Base / 253W Turbo"
            ),
            puntuacion = "4.8",
            comentarios = listOf(
                "Frecuencias altísimas para gaming",
                "Excelente para streaming simultáneo",
                "Rendimiento single-core impresionante"
            ),
            imagenUrl = "intel_core"
        ),
        
        Producto(
            codigo = "PC-003",
            nombre = "ViewSonic VP2786-4K",
            precio = "$849.990",
            descripcionCorta = "Monitor profesional 4K de 27 pulgadas con calibración de color de hardware",
            descripcionLarga = "Monitor profesional IPS 4K de 27 pulgadas con calibración de color de hardware integrada, cobertura 100% sRGB y Rec.709, 99% Adobe RGB, uniformidad de color excepcional y soporte ergonómico ajustable. Ideal para creación de contenido, edición fotográfica y gaming.",
            categoria = "PC Gaming",
            stock = "15",
            especificaciones = listOf(
                "Tamaño: 27 pulgadas",
                "Resolución: 3840x2160 (4K UHD)",
                "Panel: IPS con calibración hardware",
                "Cobertura color: 100% sRGB, 99% Adobe RGB",
                "Conectividad: DisplayPort, HDMI, USB-C"
            ),
            puntuacion = "4.9",
            comentarios = listOf(
                "Colores perfectamente calibrados de fábrica",
                "Calidad de imagen profesional",
                "Excelente para diseño y gaming"
            ),
            imagenUrl = "viewsonic"
        )
    )
    
    private val productos: List<Producto>
        get() = _productosMutables.toList()
    
    fun obtenerTodosLosProductos(): List<Producto> = productos
    
    fun obtenerProductosDestacados(): List<Producto> = productos.take(6)
    
    fun buscarProductos(query: String): List<Producto> {
        if (query.isBlank()) return productos
        
        val queryLower = query.lowercase()
        return productos.filter { producto ->
            producto.nombre.lowercase().contains(queryLower) ||
            producto.descripcionCorta.lowercase().contains(queryLower) ||
            producto.categoria.lowercase().contains(queryLower)
        }
    }
    
    fun obtenerProductosPorCategoria(categoria: String): List<Producto> {
        return productos.filter { it.categoria == categoria }
    }
    
    fun obtenerProductoPorCodigo(codigo: String): Producto? {
        return productos.find { it.codigo == codigo }
    }
    
    fun obtenerCategorias(): List<String> {
        return productos.map { it.categoria }.distinct().sorted()
    }
    
    // Métodos para administración de productos
    fun actualizarStock(codigo: String, nuevoStock: Int) {
        val index = _productosMutables.indexOfFirst { it.codigo == codigo }
        if (index != -1) {
            _productosMutables[index] = _productosMutables[index].copy(stock = nuevoStock.toString())
        }
    }
    
    fun agregarProducto(producto: Producto) {
        // Verificar que no exista un producto con el mismo código
        if (_productosMutables.none { it.codigo == producto.codigo }) {
            _productosMutables.add(producto)
        } else {
            throw IllegalArgumentException("Ya existe un producto con el código ${producto.codigo}")
        }
    }
    
    fun eliminarProducto(codigo: String) {
        _productosMutables.removeIf { it.codigo == codigo }
    }
    
    fun actualizarProducto(producto: Producto) {
        val index = _productosMutables.indexOfFirst { it.codigo == producto.codigo }
        if (index != -1) {
            _productosMutables[index] = producto
        }
    }
}

