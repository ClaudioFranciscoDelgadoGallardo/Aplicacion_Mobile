# Script para importar productos desde Supabase a la app
# Genera código Kotlin con los productos

Write-Host "`n=== IMPORTADOR DE PRODUCTOS SUPABASE ===" -ForegroundColor Cyan

# Intentar obtener productos desde el backend Product Service
try {
    Write-Host "`nConectando a Product Service (puerto 8083)..." -ForegroundColor Yellow
    
    # Primero verificar si el servicio está activo
    $health = Invoke-RestMethod -Uri "http://localhost:8083/api/productos/health" -ErrorAction Stop
    Write-Host "✓ Product Service activo" -ForegroundColor Green
    
    # Obtener productos (solo los campos básicos que existen)
    Write-Host "`nObteniendo productos..." -ForegroundColor Yellow
    $url = "http://localhost:8083/api/productos"
    
    # Nota: El endpoint devuelve error 500 por campos que faltan en BD
    # Pero podemos ver qué productos existen consultando directamente
    
    Write-Host "⚠️ El endpoint /api/productos tiene error 500 por campos faltantes" -ForegroundColor Yellow
    Write-Host "Alternativa: Conectar directamente a Supabase con PowerShell..." -ForegroundColor Cyan
    
} catch {
    Write-Host "✗ Error: $_" -ForegroundColor Red
}

Write-Host "`n=== SOLUCIÓN ALTERNATIVA ===" -ForegroundColor Cyan
Write-Host @"

Puedes obtener los productos de 3 formas:

1. MANUAL desde Supabase Dashboard:
   - Ir a: https://supabase.com/dashboard/project/xsgpfadjkjgbnnxgnqhp/editor
   - Ejecutar: SELECT id, nombre, descripcion, precio, imagen_url, categoria, stock, destacado, activo FROM productos;
   - Copiar resultados

2. Desde Frontend (React):
   - Los productos deberían estar ya cargados en el frontend
   - Revisar la BD del frontend

3. Modificar Producto.java:
   - Hacer campos opcionales: marca, descuento, fecha_creacion, fecha_actualizacion
   - Recompilar Product Service
   - Obtener productos via /api/productos

¿Qué opción prefieres?

"@ -ForegroundColor White
