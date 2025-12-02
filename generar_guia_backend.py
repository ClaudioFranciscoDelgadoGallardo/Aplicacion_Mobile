#!/usr/bin/env python
# -*- coding: utf-8 -*-
from reportlab.lib.pagesizes import letter
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak, Table, TableStyle, ListFlowable, ListItem
from reportlab.lib.enums import TA_LEFT, TA_CENTER, TA_JUSTIFY
from reportlab.lib import colors
from datetime import datetime

def crear_guia_backend():
    pdf_filename = "Guia_Integracion_Backend_Android.pdf"
    doc = SimpleDocTemplate(pdf_filename, pagesize=letter,
                          topMargin=0.5*inch, bottomMargin=0.5*inch,
                          leftMargin=0.75*inch, rightMargin=0.75*inch)
    
    story = []
    styles = getSampleStyleSheet()
    
    # Estilos personalizados
    title_style = ParagraphStyle(
        'CustomTitle',
        parent=styles['Heading1'],
        fontSize=24,
        textColor=colors.HexColor('#39FF14'),
        alignment=TA_CENTER,
        spaceAfter=30,
        fontName='Helvetica-Bold'
    )
    
    heading_style = ParagraphStyle(
        'CustomHeading',
        parent=styles['Heading2'],
        fontSize=16,
        textColor=colors.HexColor('#39FF14'),
        spaceAfter=12,
        spaceBefore=12,
        fontName='Helvetica-Bold'
    )
    
    subheading_style = ParagraphStyle(
        'CustomSubHeading',
        parent=styles['Heading3'],
        fontSize=13,
        textColor=colors.HexColor('#666666'),
        spaceAfter=8,
        fontName='Helvetica-Bold'
    )
    
    normal_style = ParagraphStyle(
        'CustomNormal',
        parent=styles['Normal'],
        fontSize=10,
        alignment=TA_JUSTIFY,
        spaceAfter=6
    )
    
    code_style = ParagraphStyle(
        'Code',
        parent=styles['Code'],
        fontSize=8,
        textColor=colors.HexColor('#0066CC'),
        leftIndent=20,
        fontName='Courier',
        spaceAfter=4
    )
    
    # PORTADA
    story.append(Spacer(1, 1.5*inch))
    story.append(Paragraph("GUÍA DE INTEGRACIÓN", title_style))
    story.append(Paragraph("Backend + App Móvil Android", heading_style))
    story.append(Spacer(1, 0.5*inch))
    story.append(Paragraph("Arquitectura de Microservicios con Docker", normal_style))
    story.append(Paragraph("Spring Boot + Android Studio + PostgreSQL", normal_style))
    story.append(Spacer(1, 0.3*inch))
    story.append(Paragraph(f"Fecha: {datetime.now().strftime('%d de diciembre de 2025')}", normal_style))
    story.append(PageBreak())
    
    # ÍNDICE
    story.append(Paragraph("ÍNDICE", heading_style))
    story.append(Spacer(1, 0.2*inch))
    
    indice_items = [
        "1. Arquitectura General del Sistema",
        "2. Configuración del Backend (Docker)",
        "3. Microservicios Disponibles",
        "4. Configuración de la App Móvil",
        "5. Conexión Emulador vs Dispositivo Físico",
        "6. Endpoints de la API",
        "7. Flujo de Autenticación (JWT)",
        "8. Pasos de Implementación",
        "9. Testing y Troubleshooting",
        "10. Comandos Útiles"
    ]
    
    for item in indice_items:
        story.append(Paragraph(item, normal_style))
    
    story.append(PageBreak())
    
    # 1. ARQUITECTURA
    story.append(Paragraph("1. ARQUITECTURA GENERAL DEL SISTEMA", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Componentes del Sistema:", subheading_style))
    
    arquitectura_data = [
        ["Componente", "Tecnología", "Puerto", "Función"],
        ["API Gateway", "Spring Boot", "8080", "Punto de entrada único"],
        ["Auth Service", "Spring Boot + JWT", "8081", "Autenticación y seguridad"],
        ["User Service", "Spring Boot", "8082", "Gestión de usuarios"],
        ["Product Service", "Spring Boot", "8083", "CRUD de productos"],
        ["Order Service", "Spring Boot", "8084", "Gestión de pedidos"],
        ["Notification Service", "Spring Boot", "8085", "Notificaciones push"],
        ["File Service", "Spring Boot", "8086", "Upload de archivos"],
        ["PostgreSQL", "Database", "5432", "Base de datos relacional"],
        ["App Android", "Kotlin + Compose", "-", "Cliente móvil"]
    ]
    
    arq_table = Table(arquitectura_data, colWidths=[1.5*inch, 1.4*inch, 0.8*inch, 2.3*inch])
    arq_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 9),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 8),
    ]))
    story.append(arq_table)
    story.append(Spacer(1, 0.2*inch))
    
    story.append(Paragraph("Flujo de Comunicación:", subheading_style))
    story.append(Paragraph(
        "App Android → API Gateway (8080) → Microservicio específico → PostgreSQL → Respuesta JSON",
        normal_style
    ))
    
    story.append(PageBreak())
    
    # 2. CONFIGURACIÓN DOCKER
    story.append(Paragraph("2. CONFIGURACIÓN DEL BACKEND CON DOCKER", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Archivo Docker Compose:", subheading_style))
    story.append(Paragraph("• <b>Ubicación:</b> Backend/docker-compose.yml", code_style))
    story.append(Paragraph("• <b>Función:</b> Orquesta todos los microservicios y la base de datos", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Paso 1: Prerequisitos", subheading_style))
    story.append(Paragraph("✓ Docker Desktop instalado y ejecutándose", normal_style))
    story.append(Paragraph("✓ Puerto 8080 disponible (API Gateway)", normal_style))
    story.append(Paragraph("✓ Puertos 8081-8086 disponibles (Microservicios)", normal_style))
    story.append(Paragraph("✓ Puerto 5432 disponible (PostgreSQL)", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Paso 2: Iniciar el Backend", subheading_style))
    story.append(Paragraph("<b>Comando PowerShell:</b>", normal_style))
    story.append(Paragraph("cd C:\\Users\\Asgard\\Aplicacion_Mobile_RodDev\\Backend", code_style))
    story.append(Paragraph("docker-compose up -d --build", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("<b>Explicación:</b>", normal_style))
    story.append(Paragraph("• <font name='Courier'>-d</font>: Modo detached (en segundo plano)", normal_style))
    story.append(Paragraph("• <font name='Courier'>--build</font>: Reconstruye las imágenes si hay cambios", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Paso 3: Verificar Estado", subheading_style))
    story.append(Paragraph("docker-compose ps", code_style))
    story.append(Paragraph("Deberías ver todos los servicios con estado 'Up'", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Paso 4: Ver Logs (Troubleshooting)", subheading_style))
    story.append(Paragraph("docker-compose logs -f", code_style))
    story.append(Paragraph("docker-compose logs -f auth-service", code_style))
    story.append(Paragraph("docker-compose logs -f api-gateway", code_style))
    
    story.append(PageBreak())
    
    # 3. MICROSERVICIOS
    story.append(Paragraph("3. MICROSERVICIOS DISPONIBLES", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("A. API GATEWAY (Puerto 8080)", subheading_style))
    story.append(Paragraph("• <b>Función:</b> Punto de entrada único para todas las peticiones", normal_style))
    story.append(Paragraph("• <b>URL:</b> http://localhost:8080 o http://10.0.2.2:8080 (emulador)", normal_style))
    story.append(Paragraph("• <b>Responsabilidad:</b> Enrutamiento, balanceo de carga, autenticación", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("B. AUTH SERVICE (Puerto 8081)", subheading_style))
    story.append(Paragraph("• <b>Endpoints:</b>", normal_style))
    story.append(Paragraph("  - POST /api/auth/login", code_style))
    story.append(Paragraph("  - POST /api/auth/register", code_style))
    story.append(Paragraph("  - GET /api/auth/validate", code_style))
    story.append(Paragraph("• <b>Tecnología:</b> JWT (JSON Web Tokens)", normal_style))
    story.append(Paragraph("• <b>Token expiration:</b> 24 horas", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("C. USER SERVICE (Puerto 8082)", subheading_style))
    story.append(Paragraph("• <b>Endpoints:</b>", normal_style))
    story.append(Paragraph("  - GET /api/usuarios (Listar todos)", code_style))
    story.append(Paragraph("  - GET /api/usuarios/{id} (Por ID)", code_style))
    story.append(Paragraph("  - GET /api/usuarios/perfil (Perfil actual)", code_style))
    story.append(Paragraph("  - PUT /api/usuarios/{id} (Actualizar)", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("D. PRODUCT SERVICE (Puerto 8083)", subheading_style))
    story.append(Paragraph("• <b>Endpoints:</b>", normal_style))
    story.append(Paragraph("  - GET /api/productos (Todos los productos)", code_style))
    story.append(Paragraph("  - GET /api/productos/{id} (Por ID)", code_style))
    story.append(Paragraph("  - GET /api/productos/categoria/{cat} (Por categoría)", code_style))
    story.append(Paragraph("  - GET /api/productos/buscar?q={query} (Búsqueda)", code_style))
    story.append(Paragraph("  - POST /api/productos (Crear - Admin)", code_style))
    story.append(Paragraph("  - PUT /api/productos/{id} (Actualizar - Admin)", code_style))
    story.append(Paragraph("  - DELETE /api/productos/{id} (Eliminar - Admin)", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("E. ORDER SERVICE (Puerto 8084)", subheading_style))
    story.append(Paragraph("• <b>Endpoints:</b>", normal_style))
    story.append(Paragraph("  - GET /api/ordenes (Todas las órdenes - Admin)", code_style))
    story.append(Paragraph("  - GET /api/ordenes/usuario/{id} (Por usuario)", code_style))
    story.append(Paragraph("  - POST /api/ordenes (Crear orden)", code_style))
    story.append(Paragraph("  - PUT /api/ordenes/{id}/estado (Cambiar estado)", code_style))
    
    story.append(PageBreak())
    
    # 4. CONFIGURACIÓN APP MÓVIL
    story.append(Paragraph("4. CONFIGURACIÓN DE LA APP MÓVIL", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Archivos de Configuración Creados:", subheading_style))
    
    archivos_config = [
        ["Archivo", "Ruta", "Función"],
        ["ApiConfig.kt", "app/src/main/java/com/levelup/gamer/network/", "Configuración URLs"],
        ["NetworkModels.kt", "app/src/main/java/com/levelup/gamer/network/models/", "DTOs y requests"],
        ["RetrofitClient.kt", "app/src/main/java/com/levelup/gamer/network/", "Cliente HTTP"],
        ["ApiService.kt", "app/src/main/java/com/levelup/gamer/network/", "Interfaz endpoints"],
    ]
    
    config_table = Table(archivos_config, colWidths=[1.5*inch, 3*inch, 2*inch])
    config_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 9),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 8),
    ]))
    story.append(config_table)
    story.append(Spacer(1, 0.2*inch))
    
    story.append(Paragraph("Dependencias en build.gradle.kts:", subheading_style))
    story.append(Paragraph("implementation(\"com.squareup.retrofit2:retrofit:2.9.0\")", code_style))
    story.append(Paragraph("implementation(\"com.squareup.retrofit2:converter-gson:2.9.0\")", code_style))
    story.append(Paragraph("implementation(\"com.squareup.okhttp3:logging-interceptor:4.12.0\")", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Permisos en AndroidManifest.xml:", subheading_style))
    story.append(Paragraph('&lt;uses-permission android:name="android.permission.INTERNET" /&gt;', code_style))
    story.append(Paragraph('&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /&gt;', code_style))
    
    story.append(PageBreak())
    
    # 5. CONEXIÓN EMULADOR VS DISPOSITIVO
    story.append(Paragraph("5. CONEXIÓN: EMULADOR VS DISPOSITIVO FÍSICO", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("IMPORTANTE: Configuración de URL según entorno", subheading_style))
    story.append(Spacer(1, 0.1*inch))
    
    conexion_data = [
        ["Entorno", "URL Base", "Explicación"],
        ["Emulador Android", "http://10.0.2.2:8080/", "IP especial que apunta al localhost de la máquina host"],
        ["Dispositivo Físico (misma red)", "http://192.168.X.XXX:8080/", "IP local de tu PC en la red WiFi"],
        ["Producción", "https://api.levelupgamer.com/", "Dominio público con HTTPS"],
    ]
    
    conexion_table = Table(conexion_data, colWidths=[1.8*inch, 2.2*inch, 2.5*inch])
    conexion_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 9),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 8),
    ]))
    story.append(conexion_table))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Cómo obtener tu IP local (para dispositivo físico):", subheading_style))
    story.append(Paragraph("<b>Windows PowerShell:</b>", normal_style))
    story.append(Paragraph("ipconfig | findstr IPv4", code_style))
    story.append(Paragraph("Busca la línea: IPv4 Address. . . . . . . . . . . : 192.168.X.XXX", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Cambiar URL en ApiConfig.kt:", subheading_style))
    story.append(Paragraph("const val BASE_URL = \"http://10.0.2.2:8080/\"  // Para emulador", code_style))
    story.append(Paragraph("// O", code_style))
    story.append(Paragraph("const val BASE_URL = \"http://192.168.1.100:8080/\"  // Para dispositivo", code_style))
    
    story.append(PageBreak())
    
    # 6. ENDPOINTS DE LA API
    story.append(Paragraph("6. ENDPOINTS DE LA API (Ejemplos de Uso)", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("LOGIN (Autenticación)", subheading_style))
    story.append(Paragraph("<b>POST</b> http://10.0.2.2:8080/api/auth/login", code_style))
    story.append(Paragraph("<b>Body (JSON):</b>", normal_style))
    story.append(Paragraph('{', code_style))
    story.append(Paragraph('  "email": "admlvlup@lvlup.cl",', code_style))
    story.append(Paragraph('  "password": "admin123"', code_style))
    story.append(Paragraph('}', code_style))
    story.append(Paragraph("<b>Response:</b>", normal_style))
    story.append(Paragraph('{', code_style))
    story.append(Paragraph('  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",', code_style))
    story.append(Paragraph('  "usuario": { "id": 1, "email": "...", "isAdmin": true }', code_style))
    story.append(Paragraph('}', code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("OBTENER PRODUCTOS", subheading_style))
    story.append(Paragraph("<b>GET</b> http://10.0.2.2:8080/api/productos", code_style))
    story.append(Paragraph("<b>Headers:</b> (opcional, público)", normal_style))
    story.append(Paragraph("<b>Response:</b>", normal_style))
    story.append(Paragraph('[', code_style))
    story.append(Paragraph('  {', code_style))
    story.append(Paragraph('    "id": 1, "codigo": "PS5001", "nombre": "PlayStation 5",', code_style))
    story.append(Paragraph('    "precio": 499990, "stock": 15, "categoria": "consolas"', code_style))
    story.append(Paragraph('  },', code_style))
    story.append(Paragraph('  ...', code_style))
    story.append(Paragraph(']', code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("CREAR ORDEN/PEDIDO", subheading_style))
    story.append(Paragraph("<b>POST</b> http://10.0.2.2:8080/api/ordenes", code_style))
    story.append(Paragraph("<b>Headers:</b> Authorization: Bearer {token}", code_style))
    story.append(Paragraph("<b>Body (JSON):</b>", normal_style))
    story.append(Paragraph('{', code_style))
    story.append(Paragraph('  "usuarioId": 1,', code_style))
    story.append(Paragraph('  "items": [', code_style))
    story.append(Paragraph('    { "productoId": 1, "cantidad": 2, "precio": 49990 }', code_style))
    story.append(Paragraph('  ],', code_style))
    story.append(Paragraph('  "metodoPago": "TARJETA"', code_style))
    story.append(Paragraph('}', code_style))
    
    story.append(PageBreak())
    
    # 7. FLUJO DE AUTENTICACIÓN
    story.append(Paragraph("7. FLUJO DE AUTENTICACIÓN JWT", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Paso a Paso:", subheading_style))
    story.append(Paragraph("1. <b>Usuario ingresa credenciales</b> en LoginScreen", normal_style))
    story.append(Paragraph("2. <b>App envía POST /api/auth/login</b> con email y password", normal_style))
    story.append(Paragraph("3. <b>Backend valida</b> credenciales en base de datos", normal_style))
    story.append(Paragraph("4. <b>Backend genera token JWT</b> firmado con clave secreta", normal_style))
    story.append(Paragraph("5. <b>App recibe token</b> y lo guarda en SharedPreferences/DataStore", normal_style))
    story.append(Paragraph("6. <b>Para cada petición protegida</b>, app incluye:", normal_style))
    story.append(Paragraph("   Header: Authorization: Bearer {token}", code_style))
    story.append(Paragraph("7. <b>Backend verifica token</b> en cada request", normal_style))
    story.append(Paragraph("8. Si token inválido/expirado → <b>Error 401 Unauthorized</b>", normal_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Implementación en Android:", subheading_style))
    story.append(Paragraph("// Guardar token después de login exitoso", code_style))
    story.append(Paragraph("dataStore.saveAuthToken(response.token)", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("// Incluir token en requests", code_style))
    story.append(Paragraph("val token = dataStore.getAuthToken()", code_style))
    story.append(Paragraph("apiService.getPerfilUsuario(\"Bearer $token\")", code_style))
    
    story.append(PageBreak())
    
    # 8. PASOS DE IMPLEMENTACIÓN
    story.append(Paragraph("8. PASOS DE IMPLEMENTACIÓN COMPLETA", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("FASE 1: Preparar Backend", subheading_style))
    story.append(Paragraph("□ Verificar Docker Desktop ejecutándose", normal_style))
    story.append(Paragraph("□ cd Backend", code_style))
    story.append(Paragraph("□ docker-compose up -d --build", code_style))
    story.append(Paragraph("□ docker-compose ps (verificar todos 'Up')", code_style))
    story.append(Paragraph("□ Probar API Gateway: http://localhost:8080/api/productos", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("FASE 2: Configurar App Android", subheading_style))
    story.append(Paragraph("□ Verificar dependencias Retrofit en build.gradle.kts", normal_style))
    story.append(Paragraph("□ Sync Gradle", normal_style))
    story.append(Paragraph("□ Configurar BASE_URL en ApiConfig.kt", normal_style))
    story.append(Paragraph("  - Emulador: http://10.0.2.2:8080/", code_style))
    story.append(Paragraph("  - Dispositivo: http://TU_IP:8080/", code_style))
    story.append(Paragraph("□ Verificar permisos INTERNET en AndroidManifest.xml", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("FASE 3: Integrar con ViewModels", subheading_style))
    story.append(Paragraph("□ AuthViewModel: Usar apiService.login() y apiService.register()", normal_style))
    story.append(Paragraph("□ HomeViewModel: Usar apiService.getAllProductos()", normal_style))
    story.append(Paragraph("□ AdminViewModel: Usar apiService.createProducto(), updateStock(), etc.", normal_style))
    story.append(Paragraph("□ CartViewModel/PedidosViewModel: Usar apiService.createOrden()", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("FASE 4: Testing", subheading_style))
    story.append(Paragraph("□ Probar login con credenciales de prueba", normal_style))
    story.append(Paragraph("□ Verificar carga de productos desde backend", normal_style))
    story.append(Paragraph("□ Probar crear orden/pedido", normal_style))
    story.append(Paragraph("□ Verificar funciones de admin (si aplica)", normal_style))
    story.append(Paragraph("□ Revisar logs con docker-compose logs -f", code_style))
    
    story.append(PageBreak())
    
    # 9. TESTING Y TROUBLESHOOTING
    story.append(Paragraph("9. TESTING Y TROUBLESHOOTING", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Problemas Comunes:", subheading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("❌ Error: Unable to resolve host / Connection refused", subheading_style))
    story.append(Paragraph("✓ <b>Causa:</b> URL incorrecta o backend no está ejecutándose", normal_style))
    story.append(Paragraph("✓ <b>Solución:</b>", normal_style))
    story.append(Paragraph("  1. Verificar docker-compose ps (todos los servicios 'Up')", normal_style))
    story.append(Paragraph("  2. Verificar URL: 10.0.2.2 para emulador, IP local para dispositivo", normal_style))
    story.append(Paragraph("  3. Hacer ping desde terminal: curl http://localhost:8080/api/productos", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("❌ Error 401 Unauthorized", subheading_style))
    story.append(Paragraph("✓ <b>Causa:</b> Token inválido, expirado o no enviado", normal_style))
    story.append(Paragraph("✓ <b>Solución:</b>", normal_style))
    story.append(Paragraph("  1. Verificar que el header Authorization esté incluido", normal_style))
    story.append(Paragraph("  2. Formato: \"Bearer {token}\" (con espacio después de Bearer)", normal_style))
    story.append(Paragraph("  3. Hacer login nuevamente para obtener token fresco", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("❌ Error 404 Not Found", subheading_style))
    story.append(Paragraph("✓ <b>Causa:</b> Endpoint incorrecto o servicio no inicializado", normal_style))
    story.append(Paragraph("✓ <b>Solución:</b>", normal_style))
    story.append(Paragraph("  1. Verificar la ruta exacta del endpoint", normal_style))
    story.append(Paragraph("  2. Ver logs del servicio: docker-compose logs -f product-service", code_style))
    story.append(Paragraph("  3. Revisar que el servicio esté 'healthy'", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("❌ Error 500 Internal Server Error", subheading_style))
    story.append(Paragraph("✓ <b>Causa:</b> Error en el backend (base de datos, lógica, etc.)", normal_style))
    story.append(Paragraph("✓ <b>Solución:</b>", normal_style))
    story.append(Paragraph("  1. Revisar logs del servicio específico", normal_style))
    story.append(Paragraph("  2. Verificar conexión a PostgreSQL: docker-compose logs postgres", code_style))
    story.append(Paragraph("  3. Revisar estructura del request (JSON válido)", normal_style))
    
    story.append(PageBreak())
    
    # 10. COMANDOS ÚTILES
    story.append(Paragraph("10. COMANDOS ÚTILES", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Docker Compose:", subheading_style))
    story.append(Paragraph("# Iniciar todos los servicios", code_style))
    story.append(Paragraph("docker-compose up -d --build", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Ver estado de servicios", code_style))
    story.append(Paragraph("docker-compose ps", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Ver logs en tiempo real", code_style))
    story.append(Paragraph("docker-compose logs -f", code_style))
    story.append(Paragraph("docker-compose logs -f api-gateway", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Detener servicios", code_style))
    story.append(Paragraph("docker-compose stop", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Eliminar contenedores (mantener volúmenes/datos)", code_style))
    story.append(Paragraph("docker-compose down", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Eliminar TODO (incluyendo base de datos)", code_style))
    story.append(Paragraph("docker-compose down -v", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Reiniciar un servicio específico", code_style))
    story.append(Paragraph("docker-compose restart auth-service", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Testing con cURL:", subheading_style))
    story.append(Paragraph("# Test API Gateway", code_style))
    story.append(Paragraph("curl http://localhost:8080/api/productos", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Test Login", code_style))
    story.append(Paragraph('curl -X POST http://localhost:8080/api/auth/login `', code_style))
    story.append(Paragraph('  -H "Content-Type: application/json" `', code_style))
    story.append(Paragraph('  -d \'{"email":"admlvlup@lvlup.cl","password":"admin123"}\'', code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Gradle (App Android):", subheading_style))
    story.append(Paragraph("# Limpiar y reconstruir", code_style))
    story.append(Paragraph(".\\gradlew clean build", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("# Instalar en dispositivo/emulador", code_style))
    story.append(Paragraph(".\\gradlew installDebug", code_style))
    
    story.append(PageBreak())
    
    # RESUMEN EJECUTIVO
    story.append(Paragraph("RESUMEN EJECUTIVO", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Arquitectura Implementada:", subheading_style))
    story.append(Paragraph("✓ <b>Microservicios</b> con Spring Boot (7 servicios independientes)", normal_style))
    story.append(Paragraph("✓ <b>API Gateway</b> como punto de entrada único", normal_style))
    story.append(Paragraph("✓ <b>PostgreSQL</b> para persistencia de datos", normal_style))
    story.append(Paragraph("✓ <b>JWT</b> para autenticación segura", normal_style))
    story.append(Paragraph("✓ <b>Docker Compose</b> para orquestación", normal_style))
    story.append(Paragraph("✓ <b>Retrofit + OkHttp</b> en Android para consumir APIs", normal_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("URLs Críticas:", subheading_style))
    story.append(Paragraph("• <b>API Gateway (Emulador):</b> http://10.0.2.2:8080/", code_style))
    story.append(Paragraph("• <b>API Gateway (Localhost):</b> http://localhost:8080/", code_style))
    story.append(Paragraph("• <b>PostgreSQL:</b> jdbc:postgresql://localhost:5432/levelup_db", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Credenciales de Prueba:", subheading_style))
    story.append(Paragraph("• <b>Admin:</b> admlvlup@lvlup.cl / admin123", code_style))
    story.append(Paragraph("• <b>Usuario:</b> usuariolvlup@lvlup.cl / user123", code_style))
    story.append(Paragraph("• <b>PostgreSQL:</b> levelup / levelup123", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Próximos Pasos:", subheading_style))
    story.append(Paragraph("1. Iniciar backend: docker-compose up -d --build", normal_style))
    story.append(Paragraph("2. Configurar BASE_URL en ApiConfig.kt según tu entorno", normal_style))
    story.append(Paragraph("3. Integrar llamadas de red en ViewModels existentes", normal_style))
    story.append(Paragraph("4. Reemplazar datos hardcoded por datos del backend", normal_style))
    story.append(Paragraph("5. Probar flujo completo: Login → Productos → Carrito → Orden", normal_style))
    story.append(Spacer(1, 0.3*inch))
    
    story.append(Paragraph("¡Sistema Listo para Integración!", title_style))
    
    # Generar PDF
    doc.build(story)
    print(f"✅ Guía de integración generada: {pdf_filename}")
    return pdf_filename

if __name__ == "__main__":
    crear_guia_backend()
