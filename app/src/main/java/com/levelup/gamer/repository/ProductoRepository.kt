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
                1L -> "Juegos de Mesa"
                2L -> "Accesorios"
                3L -> "Consolas"
                4L -> "Videojuegos"
                5L -> "Merchandising"
                6L -> "PC Gaming"
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
    
    // **DATOS CARGADOS DESDE SUPABASE - 23 PRODUCTOS TOTALES**
    private val _productosMutables = mutableListOf(
        // === JUEGOS DE MESA (categoria_id = 1) ===
        Producto(
            codigo = "JM001",
            nombre = "Catan",
            precio = "$29.990",
            descripcionCorta = "Juego clásico de estrategia para construir y colonizar islas",
            descripcionLarga = "Juego clásico de estrategia para construir y colonizar islas. Perfecto para 3-4 jugadores, con expansiones disponibles.",
            categoria = "Juegos de Mesa",
            stock = "6",
            especificaciones = listOf(
                "Jugadores: 3-4",
                "Edad: +10 años",
                "Duración: 60-120 min"
            ),
            puntuacion = "4.5",
            comentarios = listOf("Clásico imperdible", "Muy entretenido con amigos"),
            imagenUrl = "/assets/imgs/destacado1.png"
        ),
        
        Producto(
            codigo = "JM002",
            nombre = "Ticket to Ride",
            precio = "$35.990",
            descripcionCorta = "Juego de mesa de estrategia sobre construcción de rutas ferroviarias",
            descripcionLarga = "Juego de mesa de estrategia sobre construcción de rutas ferroviarias. Para 2-5 jugadores.",
            categoria = "Juegos de Mesa",
            stock = "4",
            especificaciones = listOf(
                "Jugadores: 2-5",
                "Edad: +8 años",
                "Duración: 30-60 min"
            ),
            puntuacion = "4.7",
            comentarios = listOf("Muy estratégico", "Perfecto para familia"),
            imagenUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQREHNYrDKuRzdbhe1E4fujUpppef-loJHTRw&s"
        ),
        
        // === ACCESORIOS (categoria_id = 2) ===
        Producto(
            codigo = "AC001",
            nombre = "Control Xbox Series X",
            precio = "$53.991",
            descripcionCorta = "Control inalámbrico de última generación con tecnología háptica",
            descripcionLarga = "Control inalámbrico de última generación con tecnología háptica, gatillos adaptativos y batería recargable de larga duración.",
            categoria = "Accesorios",
            stock = "1",
            especificaciones = listOf(
                "Conectividad: Bluetooth/USB-C",
                "Batería: 40 horas",
                "Compatibilidad: Xbox Series X/S, PC"
            ),
            puntuacion = "4.8",
            comentarios = listOf("Muy cómodo", "Gran duración de batería"),
            imagenUrl = "/assets/imgs/destacado2.png"
        ),
        
        Producto(
            codigo = "AC002",
            nombre = "Auriculares Gamer RGB",
            precio = "$45.990",
            descripcionCorta = "Auriculares con sonido 7.1 surround y micrófono retráctil",
            descripcionLarga = "Auriculares con sonido 7.1 surround, micrófono retráctil con cancelación de ruido e iluminación RGB personalizable.",
            categoria = "Accesorios",
            stock = "18",
            especificaciones = listOf(
                "Sonido: 7.1 Surround",
                "Micrófono: Retráctil con cancelación ruido",
                "RGB: Personalizable"
            ),
            puntuacion = "4.6",
            comentarios = listOf("Excelente audio", "RGB muy bonito"),
            imagenUrl = "https://prophonechile.cl/wp-content/uploads/2020/05/negro4.png"
        ),
        
        Producto(
            codigo = "PROD-014",
            nombre = "Mouse Gamer RGB Pro",
            precio = "$34.990",
            descripcionCorta = "Mouse gaming con iluminación RGB personalizable",
            descripcionLarga = "Mouse gaming Logitech con iluminación RGB personalizable y precisión profesional",
            categoria = "Accesorios",
            stock = "93",
            especificaciones = listOf(
                "DPI: Hasta 16000",
                "RGB: Personalizable",
                "Marca: Logitech"
            ),
            puntuacion = "4.7",
            comentarios = listOf("Muy preciso", "Excelente relación calidad-precio"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/mouse_gamer_rgb_pro.jpg"
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
                "Controladores: Neodimio 40mm Hi-Res",
                "Micrófono: ClearCast Gen 2 bidireccional",
                "Conectividad: 2.4GHz + Bluetooth 5.0",
                "Batería: Hasta 44 horas"
            ),
            puntuacion = "4.9",
            comentarios = listOf("El mejor headset que he usado", "ANC funciona perfecto"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/audifonos.jpeg"
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
                "Switches: Cherry MX Red (lineal)",
                "Polling Rate: 8000Hz AXON Technology",
                "RGB: Per-key RGB con iCUE",
                "Material: Estructura de aluminio cepillado"
            ),
            puntuacion = "4.8",
            comentarios = listOf("Los Cherry MX son perfectos", "8000Hz se siente instantáneo"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/teclado.jpeg"
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
                "Sensor: Focus Pro 30K DPI",
                "Peso: 63g ultra ligero",
                "Switches: Ópticos Gen-3 (90M clicks)",
                "Batería: Hasta 90 horas"
            ),
            puntuacion = "4.9",
            comentarios = listOf("Precisión perfecta", "Peso ideal para FPS"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/mouse.jpeg"
        ),
        
        // === CONSOLAS (categoria_id = 3) ===
        Producto(
            codigo = "CO001",
            nombre = "PlayStation 5",
            precio = "$549.990",
            descripcionCorta = "Consola de última generación con gráficos 4K y ray tracing",
            descripcionLarga = "Consola de última generación con gráficos 4K, ray tracing en tiempo real, SSD ultra rápido y retrocompatibilidad con PS4.",
            categoria = "Consolas",
            stock = "2",
            especificaciones = listOf(
                "CPU: AMD Zen 2 de 8 núcleos",
                "GPU: AMD RDNA 2",
                "RAM: 16GB GDDR6",
                "Almacenamiento: SSD 825GB"
            ),
            puntuacion = "4.8",
            comentarios = listOf("Increíble experiencia de juego", "Tiempos de carga impresionantes"),
            imagenUrl = "/assets/imgs/destacado3.png"
        ),
        
        Producto(
            codigo = "CO002",
            nombre = "Nintendo Switch OLED",
            precio = "$349.990",
            descripcionCorta = "Consola híbrida con pantalla OLED de 7 pulgadas",
            descripcionLarga = "Consola híbrida con pantalla OLED de 7 pulgadas, 64GB de almacenamiento interno y base mejorada.",
            categoria = "Consolas",
            stock = "2",
            especificaciones = listOf(
                "Pantalla: OLED 7 pulgadas",
                "Almacenamiento: 64GB",
                "Batería: 4.5-9 horas"
            ),
            puntuacion = "4.7",
            comentarios = listOf("Pantalla OLED hermosa", "Portátil y versátil"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/1764650087214-a4lh4e.jpg"
        ),
        
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
            comentarios = listOf("Increíble experiencia de juego", "El DualSense es revolucionario"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/ps5.jpeg"
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
            comentarios = listOf("Potencia pura", "Game Pass es increíble"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/xbox_series_x.jpeg"
        ),
        
        // === VIDEOJUEGOS (categoria_id = 4) ===
        Producto(
            codigo = "VJ001",
            nombre = "The Legend of Zelda: Tears of the Kingdom",
            precio = "$56.991",
            descripcionCorta = "Aventura épica en mundo abierto exclusivo para Nintendo Switch",
            descripcionLarga = "Aventura épica en mundo abierto. Explora los cielos de Hyrule. Exclusivo para Nintendo Switch.",
            categoria = "Videojuegos",
            stock = "5",
            especificaciones = listOf(
                "Plataforma: Nintendo Switch",
                "Jugadores: 1",
                "Género: Aventura / Acción"
            ),
            puntuacion = "5.0",
            comentarios = listOf("Obra maestra", "El mejor Zelda hasta ahora"),
            imagenUrl = "https://media.vandal.net/m/74464/the-legend-of-zelda-tears-of-the-kingdom-202291410341410_1.jpg"
        ),
        
        Producto(
            codigo = "VJ002",
            nombre = "God of War Ragnarök",
            precio = "$69.990",
            descripcionCorta = "Continuación épica de la saga nórdica de Kratos y Atreus",
            descripcionLarga = "Continuación épica de la saga nórdica de Kratos y Atreus. Exclusivo para PlayStation 5.",
            categoria = "Videojuegos",
            stock = "6",
            especificaciones = listOf(
                "Plataforma: PlayStation 5",
                "Jugadores: 1",
                "Género: Acción / Aventura"
            ),
            puntuacion = "4.9",
            comentarios = listOf("Épico de principio a fin", "Gráficos impresionantes"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/god_of_war_ragnar_k.jpg"
        ),
        
        Producto(
            codigo = "GAME-001",
            nombre = "Battlefield 6",
            precio = "$57.990",
            descripcionCorta = "Shooter táctico de última generación con combate a gran escala",
            descripcionLarga = "Battlefield 6 lleva la guerra moderna a un nuevo nivel con mapas masivos de 128 jugadores, destrucción avanzada y gráficos de próxima generación. Experimenta batallas épicas con vehículos, infantería y combate táctico intenso.",
            categoria = "Videojuegos",
            stock = "50",
            especificaciones = listOf(
                "Plataforma: PS5, Xbox Series X/S, PC",
                "Jugadores: Hasta 128 online",
                "Modos: Conquista, Breakthrough, Portal",
                "Mapas: 7 mapas masivos"
            ),
            puntuacion = "4.3",
            comentarios = listOf("Batallas de 128 jugadores épicas", "Gráficos impresionantes"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/batterfield6.jpeg"
        ),
        
        Producto(
            codigo = "GAME-002",
            nombre = "Diablo IV",
            precio = "$29.990",
            descripcionCorta = "RPG de acción oscuro con combate visceral y mundo compartido",
            descripcionLarga = "Regresa al santuario en esta oscura y brutal aventura. Diablo IV ofrece un vasto mundo abierto, cinco clases únicas, combate dinámico y cooperativo, dungeons generados proceduralmente y la amenaza de Lilith, hija de Mephisto.",
            categoria = "Videojuegos",
            stock = "40",
            especificaciones = listOf(
                "Plataforma: PS5, Xbox Series X/S, PC",
                "Jugadores: 1-4 cooperativo",
                "Clases: Bárbaro, Hechicera, Pícaro, Nigromante, Druida"
            ),
            puntuacion = "4.6",
            comentarios = listOf("Addictivo como siempre", "El mundo abierto es genial"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/diablo_v.jpeg"
        ),
        
        Producto(
            codigo = "GAME-003",
            nombre = "Stellar Blade",
            precio = "$45.990",
            descripcionCorta = "Action RPG futurista con combate espectacular estilo hack and slash",
            descripcionLarga = "Una experiencia de acción cinemática ambientada en un futuro distópico. Controla a Eve en su misión para recuperar la Tierra. Combate fluido y desafiante, gráficos impresionantes y una historia épica te esperan en este exclusivo de PS5.",
            categoria = "Videojuegos",
            stock = "35",
            especificaciones = listOf(
                "Plataforma: PlayStation 5 Exclusivo",
                "Jugadores: 1 jugador",
                "Género: Action RPG / Hack and Slash",
                "Rendimiento: 4K 30fps / 1080p 60fps"
            ),
            puntuacion = "4.7",
            comentarios = listOf("Combate increíblemente satisfactorio", "Gráficos de otro nivel"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/stella_blade.jpeg"
        ),
        
        // === MERCHANDISING (categoria_id = 5) ===
        Producto(
            codigo = "FG001",
            nombre = "Figura Funko Pop Mario",
            precio = "$16.992",
            descripcionCorta = "Figura coleccionable de vinilo de Mario Bros edición limitada",
            descripcionLarga = "Figura coleccionable de vinilo de Mario Bros. Edición limitada con detalles premium.",
            categoria = "Merchandising",
            stock = "1",
            especificaciones = listOf(
                "Material: Vinilo",
                "Altura: 9cm",
                "Serie: Nintendo"
            ),
            puntuacion = "4.8",
            comentarios = listOf("Muy bonita", "Detalles excelentes"),
            imagenUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShzVgLheB325tRQbnbJl8q4NPu5ig9l2524Q&s"
        ),
        
        Producto(
            codigo = "AC33134",
            nombre = "Peluche de Mario",
            precio = "$30.000",
            descripcionCorta = "Peluche coleccionable de Mario Bros de alta calidad",
            descripcionLarga = "Peluche coleccionable de Mario Bros de alta calidad, perfecto para fanáticos de Nintendo",
            categoria = "Merchandising",
            stock = "47",
            especificaciones = listOf(
                "Material: Peluche suave",
                "Altura: 30cm",
                "Serie: Nintendo"
            ),
            puntuacion = "4.5",
            comentarios = listOf("Muy suave", "Perfecto para regalo"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/1764650194421-9jqa5n.jpg"
        ),
        
        // === PC GAMING (categoria_id = 6) ===
        Producto(
            codigo = "asdasda",
            nombre = "Hachiware",
            precio = "$33",
            descripcionCorta = "Producto especial PC Gaming",
            descripcionLarga = "Producto especial PC Gaming con características únicas",
            categoria = "PC Gaming",
            stock = "331",
            especificaciones = listOf("Especificaciones especiales"),
            puntuacion = "4.0",
            comentarios = listOf("Producto único"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/asdadad.gif"
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
                "Arquitectura: NVIDIA Blackwell",
                "Núcleos CUDA: 21,760",
                "VRAM: 32GB GDDR7",
                "Boost Clock: 2.9 GHz",
                "TDP: 600W"
            ),
            puntuacion = "5.0",
            comentarios = listOf("Rendimiento absolutamente brutal", "Gaming en 8K sin problemas"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/rtx5090.jpeg"
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
                "Caché: 36MB Intel Smart Cache"
            ),
            puntuacion = "4.8",
            comentarios = listOf("Frecuencias altísimas para gaming", "Excelente para streaming"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/intel_core.jpeg"
        ),
        
        Producto(
            codigo = "PC-003",
            nombre = "ViewSonic VP2786-4K",
            precio = "$849.990",
            descripcionCorta = "Monitor profesional 4K de 27 pulgadas con calibración de color",
            descripcionLarga = "Monitor profesional IPS 4K de 27 pulgadas con calibración de color de hardware integrada, cobertura 100% sRGB y Rec.709, 99% Adobe RGB, uniformidad de color excepcional y soporte ergonómico ajustable. Ideal para creación de contenido, edición fotográfica y gaming.",
            categoria = "PC Gaming",
            stock = "15",
            especificaciones = listOf(
                "Tamaño: 27 pulgadas",
                "Resolución: 3840x2160 (4K UHD)",
                "Panel: IPS con calibración hardware",
                "Cobertura color: 100% sRGB, 99% Adobe RGB"
            ),
            puntuacion = "4.9",
            comentarios = listOf("Colores perfectamente calibrados", "Calidad de imagen profesional"),
            imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/viewsonic.jpeg"
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
