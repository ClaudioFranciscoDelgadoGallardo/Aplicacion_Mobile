-- Script para actualizar TODAS las URLs de imágenes de productos en Supabase
-- Base URL: https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/

-- Consolas - CONS
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/ps5.jpeg' WHERE codigo = 'CONS-001';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/xbox_series_x.jpeg' WHERE codigo = 'CONS-002';

-- Juegos - GAME
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/battlefield_6.jpg' WHERE codigo = 'GAME-001';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/diablo_iv.jpeg' WHERE codigo = 'GAME-002';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/stellar_blade.jpg' WHERE codigo = 'GAME-003';

-- Accesorios - ACC
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/audifonos_gamer_rgb.jpg' WHERE codigo = 'ACC-001';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/corsair_k70_rgb_pro.jpg' WHERE codigo = 'ACC-002';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/razer_deathadder_v3_pro.jpg' WHERE codigo = 'ACC-003';

-- PC Gaming - PC
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/nvidia_geforce_rtx_5090.jpg' WHERE codigo = 'PC-001';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/intel_core_i9_14900k.jpg' WHERE codigo = 'PC-002';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/viewsonic_vp2786_4k.jpg' WHERE codigo = 'PC-003';

-- Videojuegos - VJ
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/god_of_war_ragnar_k.jpg' WHERE codigo = 'VJ002';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/zelda.jpg' WHERE codigo = 'VJ001';

-- Juegos de Mesa - JM
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/catan.jpg' WHERE codigo = 'JM001';

-- Peluches/Figuras - AC, FG
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/peluche_de_mario.jpg' WHERE codigo = 'AC33134';
-- FG001 (Figura Funko Pop Mario) - SIN IMAGEN (usar icono por defecto)
UPDATE productos SET imagen_principal = '' WHERE codigo = 'FG001';

-- Mouse - PROD
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/razer_deathadder_v3_pro.jpg' WHERE codigo = 'PROD-014';

-- Hachiware (mascota) - SIN IMAGEN (usar icono por defecto)
UPDATE productos SET imagen_principal = '' WHERE codigo = 'asdasda';

-- Control Xbox - AC
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/control_xbox_series_x.jpg' WHERE codigo = 'AC001';

-- Consolas duplicadas - CO
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/ps5.jpeg' WHERE codigo = 'CO001';
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/nintendo_switch_oled.jpg' WHERE codigo = 'CO002';

-- Audífonos - AC002
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/audifonos_gamer_rgb.jpg' WHERE codigo = 'AC002';

-- Verificar todas las actualizaciones
SELECT codigo, nombre, imagen_principal FROM productos ORDER BY codigo;
