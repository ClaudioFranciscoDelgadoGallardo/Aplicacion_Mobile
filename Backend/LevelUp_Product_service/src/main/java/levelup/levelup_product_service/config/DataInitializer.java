package levelup.levelup_product_service.config;

import levelup.levelup_product_service.model.Producto;
import levelup.levelup_product_service.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
        if (productoRepository.count() == 0) {
            logger.info("Inicializando productos de ejemplo...");

            // Productos de Consolas (categoria_id = 1)
            productoRepository.save(Producto.builder()
                    .codigo("PROD-001")
                    .nombre("PlayStation 5")
                    .descripcion("Consola de videojuegos de última generación con gráficos en 4K y SSD ultrarrápido")
                    .precioBase(new BigDecimal("450000"))
                    .precioVenta(new BigDecimal("499990"))
                    .categoriaId(1L)
                    .stockActual(50)
                    .imagenUrl("/images/ps5.jpg")
                    .destacado(true)
                    .activo(true)
                    .marca("Sony")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-002")
                    .nombre("Xbox Series X")
                    .descripcion("La consola Xbox más potente con soporte para 4K nativo y ray tracing")
                    .precioBase(new BigDecimal("400000"))
                    .precioVenta(new BigDecimal("449990"))
                    .categoriaId(1L)
                    .stockActual(35)
                    .imagenUrl("/images/xbox-series-x.jpg")
                    .destacado(true)
                    .activo(true)
                    .marca("Microsoft")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-003")
                    .nombre("Nintendo Switch OLED")
                    .descripcion("Consola híbrida con pantalla OLED de 7 pulgadas")
                    .precioBase(new BigDecimal("300000"))
                    .precioVenta(new BigDecimal("349990"))
                    .categoriaId(1L)
                    .stockActual(60)
                    .imagenUrl("/images/switch-oled.jpg")
                    .destacado(true)
                    .activo(true)
                    .marca("Nintendo")
                    .descuento(BigDecimal.ZERO)
                    .build());

            // Videojuegos (categoria_id = 2)
            productoRepository.save(Producto.builder()
                    .codigo("PROD-004")
                    .nombre("God of War Ragnarök")
                    .descripcion("Continúa la épica aventura de Kratos y Atreus en los reinos nórdicos")
                    .precioBase(new BigDecimal("60000"))
                    .precioVenta(new BigDecimal("69990"))
                    .categoriaId(2L)
                    .stockActual(100)
                    .imagenUrl("/images/god-of-war.jpg")
                    .destacado(true)
                    .activo(true)
                    .marca("Sony")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-005")
                    .nombre("Spider-Man 2")
                    .descripcion("Los Spider-Men Peter Parker y Miles Morales se enfrentan a nuevas amenazas")
                    .precioBase(new BigDecimal("60000"))
                    .precioVenta(new BigDecimal("69990"))
                    .categoriaId(2L)
                    .stockActual(80)
                    .imagenUrl("/images/spiderman2.jpg")
                    .destacado(true)
                    .activo(true)
                    .marca("Sony")
                    .descuento(new BigDecimal("10.00"))
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-006")
                    .nombre("Halo Infinite")
                    .descripcion("La legendaria saga Halo continúa con una nueva aventura épica")
                    .precioBase(new BigDecimal("50000"))
                    .precioVenta(new BigDecimal("59990"))
                    .categoriaId(2L)
                    .stockActual(70)
                    .imagenUrl("/images/halo-infinite.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Microsoft")
                    .descuento(new BigDecimal("15.00"))
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-007")
                    .nombre("Forza Horizon 5")
                    .descripcion("Carreras de mundo abierto en los vibrantes paisajes de México")
                    .precioBase(new BigDecimal("50000"))
                    .precioVenta(new BigDecimal("59990"))
                    .categoriaId(2L)
                    .stockActual(65)
                    .imagenUrl("/images/forza5.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Microsoft")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-008")
                    .nombre("The Legend of Zelda: Tears of the Kingdom")
                    .descripcion("Secuela de Breath of the Wild con nuevas mecánicas y aventuras")
                    .precioBase(new BigDecimal("50000"))
                    .precioVenta(new BigDecimal("59990"))
                    .categoriaId(2L)
                    .stockActual(90)
                    .imagenUrl("/images/zelda-totk.jpg")
                    .destacado(true)
                    .activo(true)
                    .marca("Nintendo")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-009")
                    .nombre("Super Mario Bros Wonder")
                    .descripcion("El nuevo juego de plataformas 2D de Mario con mecánicas innovadoras")
                    .precioBase(new BigDecimal("50000"))
                    .precioVenta(new BigDecimal("59990"))
                    .categoriaId(2L)
                    .stockActual(75)
                    .imagenUrl("/images/mario-wonder.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Nintendo")
                    .descuento(BigDecimal.ZERO)
                    .build());

            // Accesorios (categoria_id = 3)
            productoRepository.save(Producto.builder()
                    .codigo("PROD-010")
                    .nombre("DualSense Wireless Controller")
                    .descripcion("Control inalámbrico para PS5 con retroalimentación háptica")
                    .precioBase(new BigDecimal("60000"))
                    .precioVenta(new BigDecimal("69990"))
                    .categoriaId(3L)
                    .stockActual(120)
                    .imagenUrl("/images/dualsense.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Sony")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-011")
                    .nombre("Xbox Wireless Controller")
                    .descripcion("Control inalámbrico para Xbox Series X|S con diseño ergonómico")
                    .precioBase(new BigDecimal("50000"))
                    .precioVenta(new BigDecimal("59990"))
                    .categoriaId(3L)
                    .stockActual(100)
                    .imagenUrl("/images/xbox-controller.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Microsoft")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-012")
                    .nombre("Nintendo Switch Pro Controller")
                    .descripcion("Control Pro para Nintendo Switch con mayor duración de batería")
                    .precioBase(new BigDecimal("60000"))
                    .precioVenta(new BigDecimal("69990"))
                    .categoriaId(3L)
                    .stockActual(85)
                    .imagenUrl("/images/pro-controller.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Nintendo")
                    .descuento(BigDecimal.ZERO)
                    .build());

            productoRepository.save(Producto.builder()
                    .codigo("PROD-013")
                    .nombre("Headset Pulse 3D")
                    .descripcion("Auriculares inalámbricos para PS5 con audio 3D tempest")
                    .precioBase(new BigDecimal("85000"))
                    .precioVenta(new BigDecimal("99990"))
                    .categoriaId(3L)
                    .stockActual(60)
                    .imagenUrl("/images/pulse-3d.jpg")
                    .destacado(false)
                    .activo(true)
                    .marca("Sony")
                    .descuento(BigDecimal.ZERO)
                    .build());

            logger.info("=".repeat(70));
            logger.info("✅ {} productos de ejemplo creados exitosamente", productoRepository.count());
            logger.info("Categorías: 1=Consolas, 2=Videojuegos, 3=Accesorios");
            logger.info("Productos destacados: {}", productoRepository.findByDestacadoTrueAndActivoTrue().size());
            logger.info("=".repeat(70));
        } else {
            logger.info("Productos ya existen en la base de datos. Total: {}", productoRepository.count());
        }
    }
}
