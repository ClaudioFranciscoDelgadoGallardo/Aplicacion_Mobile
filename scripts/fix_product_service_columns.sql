-- Script para agregar las columnas faltantes en la tabla productos
-- Esto arreglará el error 500 del Product Service

-- Agregar columna marca (puede ser NULL o tener un valor por defecto)
ALTER TABLE productos 
ADD COLUMN IF NOT EXISTS marca VARCHAR(255);

-- Agregar columna descuento (diferente de descuento_porcentaje que ya existe)
ALTER TABLE productos 
ADD COLUMN IF NOT EXISTS descuento DECIMAL(10,2) DEFAULT 0.00;

-- Agregar columnas de auditoría si no existen
ALTER TABLE productos 
ADD COLUMN IF NOT EXISTS fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE productos 
ADD COLUMN IF NOT EXISTS fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Actualizar productos existentes con valores por defecto para fecha_creacion si es NULL
UPDATE productos 
SET fecha_creacion = CURRENT_TIMESTAMP 
WHERE fecha_creacion IS NULL;

UPDATE productos 
SET fecha_actualizacion = CURRENT_TIMESTAMP 
WHERE fecha_actualizacion IS NULL;

-- Verificar las columnas agregadas
SELECT column_name, data_type, is_nullable, column_default
FROM information_schema.columns
WHERE table_name = 'productos'
ORDER BY ordinal_position;
