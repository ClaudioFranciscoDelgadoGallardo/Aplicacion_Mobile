package com.levelup.gamer.repository

import com.levelup.gamer.model.Producto

class ProductoRepository {
    
    private val productos = listOf(
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
            imagenUrl = "rtx_5090.jpg"
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
            imagenUrl = "battlefield_6.jpg"
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
            imagenUrl = "diablo_4.jpg"
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
            imagenUrl = "stellar_blade.jpg"
        ),
        
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
}
