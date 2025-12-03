--- Script para insertar productos de la app móvil a Supabase
-- Estos son los 11 productos que están actualmente en ProductoRepository.kt

-- NOTA: Los campos marca, descuento, fecha_creacion, fecha_actualizacion no existen en la tabla actual
-- Solo insertamos los campos que SÍ existen en la BD

-- 1. PlayStation 5
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('CONS-001', 'PlayStation 5', 'PlayStation 5 ofrece una experiencia de juego revolucionaria con gráficos de última generación, tiempos de carga ultra rápidos gracias a su SSD personalizado, y el innovador control DualSense con retroalimentación háptica.', 'Consola de nueva generación con tecnología de trazado de rayos', 3, 499990.00, 499990.00, 350000.00, 19.00, 15, 5, 1000, '/assets/imgs/ps5.png', true, true, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 2. Xbox Series X
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('CONS-002', 'Xbox Series X', 'Xbox Series X es la consola Xbox más rápida y potente jamás creada. Con 12 Teraflops de potencia de procesamiento gráfico y tiempos de carga casi instantáneos, experimenta el gaming de nueva generación.', 'La Xbox más potente de la historia con 12 TFLOPS', 3, 479990.00, 479990.00, 330000.00, 19.00, 20, 5, 1000, '/assets/imgs/xbox_series_x.png', true, true, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 3. Battlefield 6
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('GAME-001', 'Battlefield 6', 'Battlefield 6 lleva la guerra moderna a un nuevo nivel con mapas masivos de 128 jugadores, destrucción avanzada y gráficos de próxima generación. Experimenta batallas épicas con vehículos, infantería y combate táctico intenso.', 'Shooter táctico de última generación con combate a gran escala', 4, 57990.00, 57990.00, 40000.00, 19.00, 50, 10, 1000, '/assets/imgs/batterfield6.png', true, true, true, 5.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 4. Diablo IV
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('GAME-002', 'Diablo IV', 'Regresa al santuario en esta oscura y brutal aventura. Diablo IV ofrece un vasto mundo abierto, cinco clases únicas, combate dinámico y cooperativo, dungeons generados proceduralmente y la amenaza de Lilith, hija de Mephisto.', 'RPG de acción oscuro con combate visceral y mundo compartido', 4, 29990.00, 29990.00, 20000.00, 19.00, 40, 10, 1000, '/assets/imgs/diablo_v.png', true, false, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 5. Stellar Blade
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('GAME-003', 'Stellar Blade', 'Una experiencia de acción cinemática ambientada en un futuro distópico. Controla a Eve en su misión para recuperar la Tierra. Combate fluido y desafiante, gráficos impresionantes y una historia épica te esperan en este exclusivo de PS5.', 'Action RPG futurista con combate espectacular de estilo hack and slash', 4, 45990.00, 45990.00, 32000.00, 19.00, 35, 10, 1000, '/assets/imgs/stella_blade.png', true, true, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 6. SteelSeries Arctis Nova Pro
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('ACC-001', 'SteelSeries Arctis Nova Pro', 'Auriculares gaming de gama alta con controladores de neodimio de 40mm, audio Hi-Res certificado, cancelación activa de ruido (ANC), micrófono ClearCast Gen 2 retráctil y GameDAC Gen 2 para control total del audio. Compatible con PC, PlayStation y Xbox.', 'Headset gaming premium con audio Hi-Res y cancelación de ruido activa', 2, 349990.00, 349990.00, 240000.00, 19.00, 30, 10, 1000, '/assets/imgs/audifonos.png', true, false, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 7. Corsair K70 RGB PRO
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('ACC-002', 'Corsair K70 RGB PRO', 'Teclado mecánico de alta performance con switches Cherry MX Red, tecnología AXON con polling rate de 8000Hz para latencia ultra baja, iluminación RGB por tecla personalizable con iCUE, estructura de aluminio y cable USB Type-C trenzado desmontable.', 'Teclado mecánico gaming con switches Cherry MX y polling rate de 8000Hz', 2, 169990.00, 169990.00, 120000.00, 19.00, 40, 10, 1000, '/assets/imgs/teclado.png', true, false, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 8. Razer DeathAdder V3 Pro
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('ACC-003', 'Razer DeathAdder V3 Pro', 'Mouse gaming inalámbrico ultra liviano (63g) con sensor óptico Focus Pro 30K DPI, switches ópticas Gen-3 para 90 millones de clicks, tecnología HyperSpeed Wireless para latencia de 0.25ms y batería de hasta 90 horas. Diseño ergonómico icónico mejorado.', 'Mouse gaming inalámbrico profesional con sensor Focus Pro 30K', 2, 149990.00, 149990.00, 105000.00, 19.00, 35, 10, 1000, '/assets/imgs/mouse.png', true, false, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 9. NVIDIA GeForce RTX 5090
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('PC-001', 'NVIDIA GeForce RTX 5090', 'La GPU más potente jamás creada. RTX 5090 con arquitectura Blackwell ofrece ray tracing revolucionario, DLSS 4 con IA mejorada y rendimiento extremo para gaming en 8K y creación de contenido profesional.', 'Tarjeta gráfica tope de línea con tecnología Blackwell', 6, 2400000.00, 2400000.00, 1800000.00, 19.00, 5, 2, 1000, '/assets/imgs/rtx5090.png', true, true, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 10. Intel Core i9-14900K
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('PC-002', 'Intel Core i9-14900K', 'Procesador Intel de 14ª generación con 24 núcleos (8 P-cores + 16 E-cores) y 32 hilos, frecuencias de hasta 6.0 GHz con Intel Thermal Velocity Boost, soporte DDR5-5600 y overclocking desbloqueado. Rendimiento líder para gaming competitivo y multitarea.', 'Procesador de 24 núcleos desbloqueado para gaming extremo', 6, 689990.00, 689990.00, 480000.00, 19.00, 22, 5, 1000, '/assets/imgs/intel_core.png', true, true, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- 11. ViewSonic VP2786-4K
INSERT INTO productos (codigo, nombre, descripcion, descripcion_corta, categoria_id, precio_base, precio_venta, costo, iva, stock_actual, stock_minimo, stock_maximo, imagen_principal, destacado, nuevo, oferta, descuento_porcentaje, activo)
VALUES ('PC-003', 'ViewSonic VP2786-4K', 'Monitor profesional IPS 4K de 27 pulgadas con calibración de color de hardware integrada, cobertura 100% sRGB y Rec.709, 99% Adobe RGB, uniformidad de color excepcional y soporte ergonómico ajustable. Ideal para creación de contenido, edición fotográfica y gaming.', 'Monitor profesional 4K de 27 pulgadas con calibración de color de hardware', 6, 849990.00, 849990.00, 595000.00, 19.00, 15, 5, 1000, '/assets/imgs/viewsonic.png', true, false, false, 0.00, true)
ON CONFLICT (codigo) DO NOTHING;

-- Verificar inserción
SELECT COUNT(*) as total_productos FROM productos;
