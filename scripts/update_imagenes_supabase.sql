-- Script para actualizar URLs de imágenes después de subirlas a Supabase Storage
-- Ejecutar DESPUÉS de subir las imágenes al bucket 'assets/media/productos/'

-- Base URL de Supabase Storage
-- https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/

-- Actualizar imágenes de los 11 productos de la app
UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/ps5.jpeg' WHERE codigo = 'CONS-001';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/xbox_series_x.jpeg' WHERE codigo = 'CONS-002';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/batterfield6.jpeg' WHERE codigo = 'GAME-001';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/diablo_v.jpeg' WHERE codigo = 'GAME-002';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/stella_blade.jpeg' WHERE codigo = 'GAME-003';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/audifonos.jpeg' WHERE codigo = 'ACC-001';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/teclado.jpeg' WHERE codigo = 'ACC-002';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/mouse.jpeg' WHERE codigo = 'ACC-003';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/rtx5090.jpeg' WHERE codigo = 'PC-001';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/intel_core.jpeg' WHERE codigo = 'PC-002';

UPDATE productos SET imagen_principal = 'https://xsgpfadjkjgbnnxgnqhp.supabase.co/storage/v1/object/public/assets/media/productos/viewsonic.jpeg' WHERE codigo = 'PC-003';

-- Verificar actualizaciones
SELECT codigo, nombre, imagen_principal FROM productos WHERE codigo IN ('CONS-001', 'CONS-002', 'GAME-001', 'GAME-002', 'GAME-003', 'ACC-001', 'ACC-002', 'ACC-003', 'PC-001', 'PC-002', 'PC-003');
