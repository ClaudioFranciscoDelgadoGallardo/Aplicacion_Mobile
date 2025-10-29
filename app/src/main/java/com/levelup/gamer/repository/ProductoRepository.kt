package com.levelup.gamer.repository

import com.levelup.gamer.model.Producto

/**
 * Repositorio de Productos
 * 
 * Proporciona acceso a los datos de productos de la tienda gamer.
 * En esta versión, los productos están hardcodeados. En una versión
 * de producción, estos datos vendrían de una API o base de datos.
 */
class ProductoRepository {
    
    /**
     * Lista completa de productos disponibles en la tienda
     */
    private val productos = listOf(
        // CONSOLAS
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
            imagenUrl = "ps5.jpg"
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
            imagenUrl = "xbox_series_x.jpg"
        ),
        
        Producto(
            codigo = "CONS-003",
            nombre = "Nintendo Switch OLED",
            precio = "$349.990",
            descripcionCorta = "Consola híbrida con pantalla OLED de 7 pulgadas",
            descripcionLarga = "Nintendo Switch OLED Model presenta una vibrante pantalla OLED de 7 pulgadas, soporte ajustable, base con puerto LAN, 64GB de almacenamiento interno y audio mejorado para gaming portátil.",
            categoria = "Consolas",
            stock = "30",
            especificaciones = listOf(
                "Pantalla: OLED de 7 pulgadas",
                "Resolución: 1280x720 (portátil), 1920x1080 (dock)",
                "Almacenamiento: 64GB",
                "Batería: 4.5-9 horas",
                "Modos: Portátil, sobremesa, TV"
            ),
            puntuacion = "4.6",
            comentarios = listOf(
                "La pantalla OLED se ve espectacular",
                "Perfecta para jugar en cualquier lado",
                "Biblioteca de juegos increíble"
            ),
            imagenUrl = "switch_oled.jpg"
        ),
        
        // JUEGOS
        Producto(
            codigo = "GAME-001",
            nombre = "The Legend of Zelda: Tears of the Kingdom",
            precio = "$59.990",
            descripcionCorta = "Secuela épica de Breath of the Wild para Nintendo Switch",
            descripcionLarga = "Explora los vastos paisajes de Hyrule y los cielos misteriosos en esta secuela del aclamado Breath of the Wild. Descubre nuevos poderes, enfrenta enemigos temibles y desentraña el misterio detrás de las lágrimas del reino.",
            categoria = "Juegos",
            stock = "50",
            especificaciones = listOf(
                "Plataforma: Nintendo Switch",
                "Género: Acción-Aventura",
                "Jugadores: 1",
                "Editor: Nintendo",
                "Clasificación: E10+"
            ),
            puntuacion = "4.9",
            comentarios = listOf(
                "Obra maestra absoluta",
                "Mejora todo lo que hizo BOTW",
                "Juego del año sin duda"
            ),
            imagenUrl = "zelda_totk.jpg"
        ),
        
        Producto(
            codigo = "GAME-002",
            nombre = "Elden Ring",
            precio = "$54.990",
            descripcionCorta = "RPG de acción épico del creador de Dark Souls",
            descripcionLarga = "Un nuevo mundo de fantasía oscura creado por Hidetaka Miyazaki y George R.R. Martin. Explora el vasto mundo del Anillo de Elden, enfréntate a jefes legendarios y forja tu propio destino en este épico RPG de acción.",
            categoria = "Juegos",
            stock = "40",
            especificaciones = listOf(
                "Plataforma: PS5, Xbox, PC",
                "Género: RPG de Acción",
                "Jugadores: 1-4 online",
                "Editor: Bandai Namco",
                "Clasificación: M17+"
            ),
            puntuacion = "4.8",
            comentarios = listOf(
                "Increíblemente adictivo y desafiante",
                "El mejor juego de FromSoftware",
                "Mundo abierto perfecto"
            ),
            imagenUrl = "elden_ring.jpg"
        ),
        
        Producto(
            codigo = "GAME-003",
            nombre = "God of War Ragnarök",
            precio = "$59.990",
            descripcionCorta = "Épica aventura de Kratos y Atreus en la mitología nórdica",
            descripcionLarga = "Kratos y Atreus se embarcan en un viaje mítico en busca de respuestas y aliados antes de la llegada del Ragnarök. El impresionante combate y la narrativa cinematográfica regresan en esta espectacular secuela.",
            categoria = "Juegos",
            stock = "35",
            especificaciones = listOf(
                "Plataforma: PS5, PS4",
                "Género: Acción-Aventura",
                "Jugadores: 1",
                "Editor: Sony Interactive",
                "Clasificación: M17+"
            ),
            puntuacion = "4.9",
            comentarios = listOf(
                "Historia emotiva y combate brutal",
                "Gráficos impresionantes",
                "Mejor que el anterior"
            ),
            imagenUrl = "gow_ragnarok.jpg"
        ),
        
        // ACCESORIOS
        Producto(
            codigo = "ACC-001",
            nombre = "Headset HyperX Cloud II",
            precio = "$89.990",
            descripcionCorta = "Audífonos gaming con sonido envolvente 7.1",
            descripcionLarga = "Audífonos profesionales para gaming con sonido envolvente virtual 7.1, micrófono con cancelación de ruido, almohadillas de memory foam y control de audio en línea. Compatible con PC, PS5, Xbox y Switch.",
            categoria = "Accesorios",
            stock = "60",
            especificaciones = listOf(
                "Tipo: Over-ear cerrado",
                "Drivers: 53mm",
                "Frecuencia: 15Hz-25KHz",
                "Impedancia: 60 Ohmios",
                "Conectividad: USB, 3.5mm"
            ),
            puntuacion = "4.7",
            comentarios = listOf(
                "Sonido increíble y muy cómodos",
                "El micrófono es excelente",
                "Mejor relación calidad-precio"
            ),
            imagenUrl = "hyperx_cloud2.jpg"
        ),
        
        Producto(
            codigo = "ACC-002",
            nombre = "Teclado Mecánico Razer BlackWidow V3",
            precio = "$129.990",
            descripcionCorta = "Teclado mecánico gaming con switches Razer Green",
            descripcionLarga = "Teclado mecánico de tamaño completo con switches Razer Green táctiles y clicky, iluminación RGB Chroma personalizable, reposamuñecas ergonómico y construcción de aluminio de grado militar.",
            categoria = "Accesorios",
            stock = "25",
            especificaciones = listOf(
                "Switches: Razer Green Mechanical",
                "RGB: Razer Chroma",
                "Layout: Full-size (104 teclas)",
                "Durabilidad: 80 millones de pulsaciones",
                "Conectividad: USB-C desmontable"
            ),
            puntuacion = "4.6",
            comentarios = listOf(
                "Switches muy satisfactorios",
                "RGB espectacular",
                "Construcción premium"
            ),
            imagenUrl = "razer_blackwidow.jpg"
        ),
        
        Producto(
            codigo = "ACC-003",
            nombre = "Mouse Logitech G502 HERO",
            precio = "$79.990",
            descripcionCorta = "Mouse gaming de alta precisión con sensor HERO 25K",
            descripcionLarga = "Mouse gaming legendario rediseñado con sensor HERO 25K de nueva generación, 11 botones programables, sistema de pesas ajustable y iluminación RGB LIGHTSYNC personalizable.",
            categoria = "Accesorios",
            stock = "45",
            especificaciones = listOf(
                "Sensor: HERO 25K",
                "DPI: 100-25,600",
                "Botones: 11 programables",
                "Peso: 121g (ajustable)",
                "Cable: Trenzado resistente"
            ),
            puntuacion = "4.8",
            comentarios = listOf(
                "El mejor mouse que he tenido",
                "Precisión perfecta",
                "Muy personalizable"
            ),
            imagenUrl = "logitech_g502.jpg"
        ),
        
        // PC GAMING
        Producto(
            codigo = "PC-001",
            nombre = "NVIDIA GeForce RTX 4080",
            precio = "$1.199.990",
            descripcionCorta = "Tarjeta gráfica de alta gama con tecnología Ada Lovelace",
            descripcionLarga = "Experimenta el gaming de nueva generación con la arquitectura Ada Lovelace. RTX 4080 ofrece ray tracing avanzado, DLSS 3 con generación de fotogramas y potencia extrema para gaming en 4K.",
            categoria = "PC Gaming",
            stock = "10",
            especificaciones = listOf(
                "Arquitectura: Ada Lovelace",
                "Núcleos CUDA: 9728",
                "VRAM: 16GB GDDR6X",
                "Boost Clock: 2.51 GHz",
                "TDP: 320W"
            ),
            puntuacion = "4.9",
            comentarios = listOf(
                "Bestial, corre todo a 4K 120fps",
                "El DLSS 3 es mágico",
                "Vale cada peso"
            ),
            imagenUrl = "rtx_4080.jpg"
        ),
        
        Producto(
            codigo = "PC-002",
            nombre = "AMD Ryzen 9 7950X",
            precio = "$649.990",
            descripcionCorta = "Procesador de 16 núcleos para gaming y creación de contenido",
            descripcionLarga = "Procesador insignia de AMD con 16 núcleos y 32 hilos, arquitectura Zen 4, soporte DDR5 y PCIe 5.0. Rendimiento extremo para gaming, streaming y productividad.",
            categoria = "PC Gaming",
            stock = "18",
            especificaciones = listOf(
                "Núcleos/Hilos: 16/32",
                "Frecuencia Base: 4.5 GHz",
                "Frecuencia Boost: 5.7 GHz",
                "Caché: 80MB",
                "TDP: 170W"
            ),
            puntuacion = "4.8",
            comentarios = listOf(
                "Monstruo de rendimiento",
                "Perfecto para multitarea",
                "Temperaturas controlables"
            ),
            imagenUrl = "ryzen_7950x.jpg"
        ),
        
        Producto(
            codigo = "PC-003",
            nombre = "Monitor ASUS ROG Swift PG32UQ",
            precio = "$899.990",
            descripcionCorta = "Monitor gaming 4K 144Hz con HDR 600",
            descripcionLarga = "Monitor gaming de 32 pulgadas con panel IPS 4K, 144Hz, DisplayHDR 600, G-SYNC Compatible y FreeSync Premium Pro. Experimenta colores vibrantes y acción fluida.",
            categoria = "PC Gaming",
            stock = "12",
            especificaciones = listOf(
                "Tamaño: 32 pulgadas",
                "Resolución: 3840x2160 (4K)",
                "Tasa de refresco: 144Hz",
                "Tiempo de respuesta: 1ms (GTG)",
                "Panel: IPS, HDR 600"
            ),
            puntuacion = "4.7",
            comentarios = listOf(
                "Imagen espectacular",
                "144Hz en 4K es increíble",
                "Colores muy precisos"
            ),
            imagenUrl = "asus_pg32uq.jpg"
        )
    )
    
    /**
     * Obtiene todos los productos disponibles
     */
    fun obtenerTodosLosProductos(): List<Producto> = productos
    
    /**
     * Obtiene productos destacados (los primeros 6)
     */
    fun obtenerProductosDestacados(): List<Producto> = productos.take(6)
    
    /**
     * Busca productos por nombre o descripción
     */
    fun buscarProductos(query: String): List<Producto> {
        if (query.isBlank()) return productos
        
        val queryLower = query.lowercase()
        return productos.filter { producto ->
            producto.nombre.lowercase().contains(queryLower) ||
            producto.descripcionCorta.lowercase().contains(queryLower) ||
            producto.categoria.lowercase().contains(queryLower)
        }
    }
    
    /**
     * Obtiene productos por categoría
     */
    fun obtenerProductosPorCategoria(categoria: String): List<Producto> {
        return productos.filter { it.categoria == categoria }
    }
    
    /**
     * Obtiene un producto por su código
     */
    fun obtenerProductoPorCodigo(codigo: String): Producto? {
        return productos.find { it.codigo == codigo }
    }
    
    /**
     * Obtiene todas las categorías disponibles
     */
    fun obtenerCategorias(): List<String> {
        return productos.map { it.categoria }.distinct().sorted()
    }
}
