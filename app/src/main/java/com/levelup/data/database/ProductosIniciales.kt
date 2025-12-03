package com.levelup.data.database

import com.levelup.gamer.model.Producto

/**
 * Productos de respaldo (fallback) cuando el backend no está disponible
 * Mantiene una copia local para que la app funcione offline
 */
object ProductosIniciales {
    
    fun obtenerProductos(): List<Producto> {
        return listOf(
            Producto(
                codigo = "JM001",
                nombre = "Catan",
                precio = "$29.990",
                descripcionCorta = "Juego clásico de estrategia para construir y colonizar islas",
                descripcionLarga = "Juego clásico de estrategia para construir y colonizar islas. Perfecto para 3-4 jugadores, con expansiones disponibles.",
                categoria = "Juegos de Mesa",
                stock = "6",
                especificaciones = listOf("Jugadores: 3-4", "Edad: +10 años", "Duración: 60-120 min"),
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
                especificaciones = listOf("Jugadores: 2-5", "Edad: +8 años", "Duración: 30-60 min"),
                puntuacion = "4.7",
                comentarios = listOf("Muy estratégico", "Perfecto para familia"),
                imagenUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQREHNYrDKuRzdbhe1E4fujUpppef-loJHTRw&s"
            ),
            Producto(
                codigo = "AC001",
                nombre = "Control Xbox Series X",
                precio = "$53.991",
                descripcionCorta = "Control inalámbrico de última generación con tecnología háptica",
                descripcionLarga = "Control inalámbrico de última generación con tecnología háptica, gatillos adaptativos y batería recargable de larga duración.",
                categoria = "Accesorios",
                stock = "1",
                especificaciones = listOf("Conectividad: Bluetooth/USB-C", "Batería: 40 horas", "Compatibilidad: Xbox Series X/S, PC"),
                puntuacion = "4.8",
                comentarios = listOf("Muy cómodo", "Gran duración de batería"),
                imagenUrl = "/assets/imgs/destacado2.png"
            ),
            Producto(
                codigo = "CO001",
                nombre = "PlayStation 5",
                precio = "$549.990",
                descripcionCorta = "Consola de última generación con gráficos 4K y ray tracing",
                descripcionLarga = "Consola de última generación con gráficos 4K, ray tracing en tiempo real, SSD ultra rápido y retrocompatibilidad con PS4.",
                categoria = "Consolas",
                stock = "2",
                especificaciones = listOf("CPU: AMD Zen 2 de 8 núcleos", "GPU: AMD RDNA 2", "RAM: 16GB GDDR6", "Almacenamiento: SSD 825GB"),
                puntuacion = "4.8",
                comentarios = listOf("Increíble experiencia de juego", "Tiempos de carga impresionantes"),
                imagenUrl = "/assets/imgs/destacado3.png"
            ),
            Producto(
                codigo = "CONS-001",
                nombre = "PlayStation 5",
                precio = "$499.990",
                descripcionCorta = "Consola de nueva generación con tecnología de trazado de rayos",
                descripcionLarga = "PlayStation 5 ofrece una experiencia de juego revolucionaria con gráficos de última generación, tiempos de carga ultra rápidos gracias a su SSD personalizado, y el innovador control DualSense con retroalimentación háptica.",
                categoria = "Consolas",
                stock = "15",
                especificaciones = listOf("CPU: AMD Zen 2 de 8 núcleos", "GPU: AMD RDNA 2", "RAM: 16GB GDDR6", "Almacenamiento: SSD 825GB", "Resolución: Hasta 8K"),
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
                especificaciones = listOf("CPU: AMD Zen 2 de 8 núcleos a 3.8 GHz", "GPU: 12 TFLOPS AMD RDNA 2", "RAM: 16GB GDDR6", "Almacenamiento: SSD NVMe 1TB", "FPS: Hasta 120 FPS"),
                puntuacion = "4.7",
                comentarios = listOf("Potencia pura", "Game Pass es increíble"),
                imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/xbox_series_x.jpeg"
            ),
            Producto(
                codigo = "GAME-001",
                nombre = "Battlefield 6",
                precio = "$57.990",
                descripcionCorta = "Shooter táctico de última generación con combate a gran escala",
                descripcionLarga = "Battlefield 6 lleva la guerra moderna a un nuevo nivel con mapas masivos de 128 jugadores, destrucción avanzada y gráficos de próxima generación. Experimenta batallas épicas con vehículos, infantería y combate táctico intenso.",
                categoria = "Videojuegos",
                stock = "50",
                especificaciones = listOf("Plataforma: PS5, Xbox Series X/S, PC", "Jugadores: Hasta 128 online", "Modos: Conquista, Breakthrough, Portal", "Mapas: 7 mapas masivos"),
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
                especificaciones = listOf("Plataforma: PS5, Xbox Series X/S, PC", "Jugadores: 1-4 cooperativo", "Clases: Bárbaro, Hechicera, Pícaro, Nigromante, Druida"),
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
                especificaciones = listOf("Plataforma: PlayStation 5 Exclusivo", "Jugadores: 1 jugador", "Género: Action RPG / Hack and Slash", "Rendimiento: 4K 30fps / 1080p 60fps"),
                puntuacion = "4.7",
                comentarios = listOf("Combate increíblemente satisfactorio", "Gráficos de otro nivel"),
                imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/stella_blade.jpeg"
            ),
            Producto(
                codigo = "ACC-001",
                nombre = "SteelSeries Arctis Nova Pro",
                precio = "$349.990",
                descripcionCorta = "Headset gaming premium con audio Hi-Res y cancelación de ruido activa",
                descripcionLarga = "Auriculares gaming de gama alta con controladores de neodimio de 40mm, audio Hi-Res certificado, cancelación activa de ruido (ANC), micrófono ClearCast Gen 2 retráctil y GameDAC Gen 2 para control total del audio. Compatible con PC, PlayStation y Xbox.",
                categoria = "Accesorios",
                stock = "30",
                especificaciones = listOf("Controladores: Neodimio 40mm Hi-Res", "Micrófono: ClearCast Gen 2 bidireccional", "Conectividad: 2.4GHz + Bluetooth 5.0", "Batería: Hasta 44 horas"),
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
                especificaciones = listOf("Switches: Cherry MX Red (lineal)", "Polling Rate: 8000Hz AXON Technology", "RGB: Per-key RGB con iCUE", "Material: Estructura de aluminio cepillado"),
                puntuacion = "4.8",
                comentarios = listOf("Los Cherry MX son perfectos", "8000Hz se siente instantáneo"),
                imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/teclado.jpeg"
            ),
            Producto(
                codigo = "PC-001",
                nombre = "NVIDIA GeForce RTX 5090",
                precio = "$2.400.000",
                descripcionCorta = "Tarjeta gráfica tope de línea con tecnología Blackwell",
                descripcionLarga = "La GPU más potente jamás creada. RTX 5090 con arquitectura Blackwell ofrece ray tracing revolucionario, DLSS 4 con IA mejorada y rendimiento extremo para gaming en 8K y creación de contenido profesional.",
                categoria = "PC Gaming",
                stock = "5",
                especificaciones = listOf("Arquitectura: NVIDIA Blackwell", "Núcleos CUDA: 21,760", "VRAM: 32GB GDDR7", "Boost Clock: 2.9 GHz", "TDP: 600W"),
                puntuacion = "5.0",
                comentarios = listOf("Rendimiento absolutamente brutal", "Gaming en 8K sin problemas"),
                imagenUrl = "https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/rtx5090.jpeg"
            )
        )
    }
}
