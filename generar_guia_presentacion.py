#!/usr/bin/env python
# -*- coding: utf-8 -*-
from reportlab.lib.pagesizes import letter, A4
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak, Table, TableStyle
from reportlab.lib.enums import TA_LEFT, TA_CENTER, TA_JUSTIFY
from reportlab.lib import colors
from datetime import datetime

def crear_guia_presentacion():
    pdf_filename = "Guia_Presentacion_LevelUp_Gamer.pdf"
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
        fontSize=9,
        textColor=colors.HexColor('#0066CC'),
        leftIndent=20,
        fontName='Courier'
    )
    
    # PORTADA
    story.append(Spacer(1, 1.5*inch))
    story.append(Paragraph("GUÍA DE PRESENTACIÓN", title_style))
    story.append(Paragraph("Aplicación Móvil Level-Up Gamer", heading_style))
    story.append(Spacer(1, 0.5*inch))
    story.append(Paragraph("Desarrollo de Soluciones Móviles", normal_style))
    story.append(Paragraph("Evaluación Final Transversal", normal_style))
    story.append(Spacer(1, 0.3*inch))
    story.append(Paragraph(f"Fecha: {datetime.now().strftime('%d de noviembre de 2025')}", normal_style))
    story.append(PageBreak())
    
    # ÍNDICE
    story.append(Paragraph("ÍNDICE", heading_style))
    story.append(Spacer(1, 0.2*inch))
    
    indice_items = [
        "1. Arquitectura del Proyecto",
        "2. Tecnologías Implementadas",
        "3. Estructura de Carpetas y Rutas",
        "4. Pantallas Principales",
        "5. Funcionalidades Clave",
        "6. Base de Datos",
        "7. Credenciales de Prueba",
        "8. Preguntas Frecuentes del Profesor"
    ]
    
    for item in indice_items:
        story.append(Paragraph(item, normal_style))
    
    story.append(PageBreak())
    
    # 1. ARQUITECTURA DEL PROYECTO
    story.append(Paragraph("1. ARQUITECTURA DEL PROYECTO", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("¿Cómo está diseñado este proyecto?", subheading_style))
    story.append(Paragraph(
        "Esta aplicación móvil está desarrollada en <b>Android Studio</b> usando <b>Jetpack Compose</b> "
        "con <b>Kotlin</b> como lenguaje principal. No utiliza React, Bootstrap ni tecnologías web - "
        "es una aplicación nativa de Android 100%.",
        normal_style
    ))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Patrón de Arquitectura:", subheading_style))
    story.append(Paragraph("• <b>MVVM (Model-View-ViewModel)</b>", normal_style))
    story.append(Paragraph("• <b>Repository Pattern</b> para acceso a datos", normal_style))
    story.append(Paragraph("• <b>Room Database</b> para persistencia local", normal_style))
    story.append(Paragraph("• <b>Jetpack Compose</b> para UI declarativa", normal_style))
    story.append(Spacer(1, 0.2*inch))
    
    # 2. TECNOLOGÍAS IMPLEMENTADAS
    story.append(Paragraph("2. TECNOLOGÍAS IMPLEMENTADAS", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    tecnologias_data = [
        ["Tecnología", "Versión/Descripción", "Propósito"],
        ["Android Studio", "2024.x", "IDE de desarrollo"],
        ["Kotlin", "1.9.x", "Lenguaje de programación"],
        ["Jetpack Compose", "1.5.x", "Framework UI moderno"],
        ["Material Design 3", "1.1.x", "Sistema de diseño"],
        ["Room Database", "2.6.x", "Base de datos local"],
        ["Coroutines", "1.7.x", "Programación asíncrona"],
        ["ViewModel", "2.6.x", "Gestión de estado"],
        ["Navigation Compose", "2.7.x", "Navegación entre pantallas"],
        ["Coil", "2.4.x", "Carga de imágenes"],
        ["Gson", "2.10.x", "Serialización JSON"]
    ]
    
    tech_table = Table(tecnologias_data, colWidths=[2*inch, 1.8*inch, 2.5*inch])
    tech_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 10),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 9),
    ]))
    story.append(tech_table)
    story.append(PageBreak())
    
    # 3. ESTRUCTURA DE CARPETAS Y RUTAS
    story.append(Paragraph("3. ESTRUCTURA DE CARPETAS Y RUTAS", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Ruta Base del Proyecto:", subheading_style))
    story.append(Paragraph("<font name='Courier' color='#0066CC'>C:\\Users\\Asgard\\Aplicacion_Mobile_RodDev\\</font>", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Estructura Principal:", subheading_style))
    
    rutas_estructura = """
<b>app/src/main/java/com/levelup/gamer/</b>
│
├── <b>MainActivity.kt</b> (Actividad principal - Orquestador de toda la app)
│   └── Ruta: app/src/main/java/com/levelup/gamer/MainActivity.kt
│
├── <b>ui/screens/</b> (Todas las pantallas de la aplicación)
│   ├── HomeScreen.kt (Pantalla principal con productos)
│   ├── LoginScreen.kt (Inicio de sesión)
│   ├── RegisterScreen.kt (Registro de usuarios)
│   ├── ProfileScreen.kt (Perfil con estadísticas)
│   ├── CartScreen.kt (Carrito de compras)
│   ├── ProductDetailScreen.kt (Detalle de producto)
│   ├── PedidosScreen.kt (Historial de pedidos)
│   ├── AdminScreen.kt (Panel de administrador)
│   ├── CategoriesScreen.kt (Vista de categorías)
│   ├── NewsScreen.kt (Noticias)
│   ├── ContactScreen.kt (Contacto)
│   └── SettingsScreen.kt (Configuración)
│
├── <b>viewmodel/</b> (Lógica de negocio)
│   ├── AuthViewModel.kt (Autenticación)
│   ├── HomeViewModel.kt (Productos y búsqueda)
│   ├── CartViewModel.kt (Carrito)
│   ├── ProfileViewModel.kt (Perfil de usuario)
│   ├── AdminViewModel.kt (Gestión administrativa)
│   └── PedidosViewModel.kt (Pedidos)
│
├── <b>repository/</b> (Acceso a datos)
│   ├── ProductoRepository.kt (Productos)
│   ├── auth/AuthRepository.kt (Autenticación)
│   ├── carrito/CarritoRepository.kt (Carrito)
│   ├── favoritos/FavoritosRepository.kt (Favoritos)
│   └── pedido/PedidoRepository.kt (Pedidos)
│
├── <b>data/</b> (Base de datos)
│   ├── database/AppDatabase.kt (Configuración Room)
│   ├── dao/
│   │   ├── UserDao.kt
│   │   ├── CarritoDao.kt
│   │   ├── FavoritoDao.kt
│   │   └── PedidoDao.kt
│   └── UserPreferences.kt (Preferencias)
│
└── <b>model/</b> (Modelos de datos)
    ├── UserEntity.kt (Usuario)
    ├── Producto.kt (Producto)
    ├── CarritoItem.kt (Item del carrito)
    ├── Pedido.kt (Pedido)
    └── FavoritoEntity.kt (Favorito)
"""
    
    for linea in rutas_estructura.strip().split('\n'):
        story.append(Paragraph(linea, code_style))
    
    story.append(PageBreak())
    
    # 4. PANTALLAS PRINCIPALES - RUTAS EXACTAS
    story.append(Paragraph("4. PANTALLAS PRINCIPALES - RUTAS EXACTAS", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    pantallas_data = [
        ["Pantalla", "Archivo", "Ruta Completa"],
        ["Inicio/Home", "HomeScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/HomeScreen.kt"],
        ["Login", "LoginScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/LoginScreen.kt"],
        ["Registro", "RegisterScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/RegisterScreen.kt"],
        ["Perfil", "ProfileScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/ProfileScreen.kt"],
        ["Carrito", "CartScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/CartScreen.kt"],
        ["Detalle Producto", "ProductDetailScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/ProductDetailScreen.kt"],
        ["Pedidos", "PedidosScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/PedidosScreen.kt"],
        ["Administrador", "AdminScreen.kt", "app/src/main/java/com/levelup/gamer/ui/screens/AdminScreen.kt"],
    ]
    
    pantallas_table = Table(pantallas_data, colWidths=[1.5*inch, 1.8*inch, 3.2*inch])
    pantallas_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 9),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 8),
        ('FONTNAME', (0, 1), (1, -1), 'Courier'),
    ]))
    story.append(pantallas_table)
    
    story.append(PageBreak())
    
    # 5. FUNCIONALIDADES CLAVE Y SUS UBICACIONES
    story.append(Paragraph("5. FUNCIONALIDADES CLAVE Y SUS UBICACIONES", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("A. BÚSQUEDA DE PRODUCTOS", subheading_style))
    story.append(Paragraph("<b>¿Dónde está el buscador?</b>", normal_style))
    story.append(Paragraph("• <b>Archivo:</b> HomeScreen.kt (líneas 72-93)", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/ui/screens/HomeScreen.kt", code_style))
    story.append(Paragraph("• <b>Lógica:</b> HomeViewModel.kt (función updateSearchQuery y getFilteredProducts)", code_style))
    story.append(Paragraph("• <b>Componente:</b> OutlinedTextField con placeholder 'Buscar productos...'", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("B. SISTEMA DE FAVORITOS", subheading_style))
    story.append(Paragraph("<b>¿Dónde está el botón de favoritos?</b>", normal_style))
    story.append(Paragraph("• <b>Archivo:</b> ProductDetailScreen.kt (líneas 90-110)", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/ui/screens/ProductDetailScreen.kt", code_style))
    story.append(Paragraph("• <b>Repository:</b> FavoritosRepository.kt", code_style))
    story.append(Paragraph("• <b>Base de Datos:</b> FavoritoEntity.kt + FavoritoDao.kt", code_style))
    story.append(Paragraph("• <b>Ícono:</b> Corazón rojo (favorito) / Corazón blanco (no favorito)", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("C. CARRITO DE COMPRAS", subheading_style))
    story.append(Paragraph("<b>¿Dónde se maneja el carrito?</b>", normal_style))
    story.append(Paragraph("• <b>Pantalla:</b> CartScreen.kt", code_style))
    story.append(Paragraph("• <b>ViewModel:</b> CartViewModel.kt", code_style))
    story.append(Paragraph("• <b>Repository:</b> app/src/main/java/com/levelup/gamer/repository/carrito/CarritoRepository.kt", code_style))
    story.append(Paragraph("• <b>Detalle de boleta:</b> CartScreen.kt (líneas 233-370) - CartSummary composable", code_style))
    story.append(Paragraph("• <b>Descuento DUOC:</b> Se calcula automáticamente si el email termina en @duocuc.cl", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("D. SISTEMA DE PUNTOS", subheading_style))
    story.append(Paragraph("<b>¿Dónde se calculan los puntos?</b>", normal_style))
    story.append(Paragraph("• <b>Cálculo:</b> PedidoRepository.kt (línea 31) - 5% del total", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/repository/pedido/PedidoRepository.kt", code_style))
    story.append(Paragraph("• <b>Visualización:</b> ProfileScreen.kt (UserInfoCard) + CartScreen.kt (CartSummary)", code_style))
    story.append(Paragraph("• <b>Actualización:</b> Se guardan en cada pedido y se suman en el perfil", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("E. PANEL DE ADMINISTRADOR", subheading_style))
    story.append(Paragraph("<b>¿Dónde está el panel de admin?</b>", normal_style))
    story.append(Paragraph("• <b>Archivo:</b> AdminScreen.kt (596 líneas)", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/ui/screens/AdminScreen.kt", code_style))
    story.append(Paragraph("• <b>ViewModel:</b> AdminViewModel.kt", code_style))
    story.append(Paragraph("• <b>Acceso:</b> Solo usuarios con isAdmin = true", normal_style))
    story.append(Paragraph("• <b>Funciones:</b> Agregar/quitar stock, crear productos, eliminar productos", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("F. BOTÓN CERRAR SESIÓN", subheading_style))
    story.append(Paragraph("<b>¿Dónde está el botón de cerrar sesión?</b>", normal_style))
    story.append(Paragraph("• <b>Ubicación:</b> ProfileScreen.kt (componente LogoutButton, líneas 466-520)", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/ui/screens/ProfileScreen.kt", code_style))
    story.append(Paragraph("• <b>Acción:</b> Muestra diálogo de confirmación antes de cerrar sesión", normal_style))
    story.append(Paragraph("• <b>Nota:</b> Ya NO está en SettingsScreen, se movió al perfil", normal_style))
    
    story.append(PageBreak())
    
    # 6. BASE DE DATOS
    story.append(Paragraph("6. BASE DE DATOS - ROOM DATABASE", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Configuración Principal:", subheading_style))
    story.append(Paragraph("• <b>Archivo:</b> AppDatabase.kt", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/data/database/AppDatabase.kt", code_style))
    story.append(Paragraph("• <b>Versión:</b> 6", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Tablas de la Base de Datos:", subheading_style))
    
    tablas_db = [
        ["Tabla", "Entity", "DAO", "Propósito"],
        ["users", "UserEntity.kt", "UserDao.kt", "Usuarios registrados"],
        ["carritos", "CarritoItem.kt", "CarritoDao.kt", "Items del carrito"],
        ["pedidos", "Pedido.kt", "PedidoDao.kt", "Historial de pedidos"],
        ["favoritos", "FavoritoEntity.kt", "FavoritoDao.kt", "Productos favoritos"],
    ]
    
    db_table = Table(tablas_db, colWidths=[1.2*inch, 1.5*inch, 1.3*inch, 2.5*inch])
    db_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 9),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 9),
    ]))
    story.append(db_table)
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Campos Importantes de UserEntity:", subheading_style))
    story.append(Paragraph("• <b>id:</b> Identificador único", code_style))
    story.append(Paragraph("• <b>email:</b> Correo electrónico (único)", code_style))
    story.append(Paragraph("• <b>password:</b> Contraseña", code_style))
    story.append(Paragraph("• <b>nombre:</b> Nombre del usuario", code_style))
    story.append(Paragraph("• <b>isAdmin:</b> Boolean - true para administradores", code_style))
    story.append(Paragraph("• <b>descuentoPorcentaje:</b> Descuento automático (20% para @duocuc.cl)", code_style))
    story.append(Paragraph("• <b>puntos:</b> Puntos acumulados", code_style))
    story.append(Paragraph("• <b>totalCompras:</b> Cantidad de compras realizadas", code_style))
    
    story.append(PageBreak())
    
    # 7. CREDENCIALES DE PRUEBA
    story.append(Paragraph("7. CREDENCIALES DE PRUEBA", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Usuario Administrador:", subheading_style))
    story.append(Paragraph("• <b>Email:</b> admlvlup@lvlup.cl", code_style))
    story.append(Paragraph("• <b>Password:</b> admin123", code_style))
    story.append(Paragraph("• <b>Permisos:</b> Acceso completo al panel de administrador", normal_style))
    story.append(Paragraph("• <b>Ubicación en código:</b> AuthRepository.kt (línea 76-84)", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Usuario Normal:", subheading_style))
    story.append(Paragraph("• <b>Email:</b> usuariolvlup@lvlup.cl", code_style))
    story.append(Paragraph("• <b>Password:</b> user123", code_style))
    story.append(Paragraph("• <b>Permisos:</b> Usuario estándar sin acceso admin", normal_style))
    story.append(Paragraph("• <b>Ubicación en código:</b> AuthRepository.kt (línea 86-93)", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Nota Importante:", subheading_style))
    story.append(Paragraph(
        "Estos usuarios se crean automáticamente al iniciar la app por primera vez "
        "si no existen. El código está en AuthRepository.kt en la función initializeDefaultUsers().",
        normal_style
    ))
    
    story.append(PageBreak())
    
    # 8. PREGUNTAS FRECUENTES
    story.append(Paragraph("8. PREGUNTAS FRECUENTES DEL PROFESOR", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("P1: ¿Este botón dónde está?", subheading_style))
    story.append(Paragraph(
        "<b>R:</b> Buscar el componente en el archivo correspondiente. Por ejemplo:",
        normal_style
    ))
    story.append(Paragraph("• Botón 'Agregar al Carrito': ProductDetailScreen.kt (línea ~180)", code_style))
    story.append(Paragraph("• Botón 'Finalizar Compra': CartScreen.kt (línea ~140)", code_style))
    story.append(Paragraph("• Botón 'Favoritos': ProductDetailScreen.kt (línea 90-110)", code_style))
    story.append(Paragraph("• Botón 'Cerrar Sesión': ProfileScreen.kt (línea 466)", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("P2: Si cambio algo aquí, ¿dónde más debo modificar?", subheading_style))
    story.append(Paragraph("<b>R:</b> Depende del tipo de cambio:", normal_style))
    story.append(Paragraph(
        "• <b>Cambio en UI:</b> Solo el archivo .kt de la pantalla (por ejemplo, HomeScreen.kt)",
        normal_style
    ))
    story.append(Paragraph(
        "• <b>Cambio en datos:</b> Model (Entity) → DAO → Repository → ViewModel → Screen",
        normal_style
    ))
    story.append(Paragraph(
        "• <b>Nueva funcionalidad:</b> Crear en todos los niveles (Model, DAO, Repository, ViewModel, UI)",
        normal_style
    ))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("P3: ¿Dónde se controla este error?", subheading_style))
    story.append(Paragraph("<b>R:</b> Los errores se manejan en:", normal_style))
    story.append(Paragraph("• <b>Validaciones de formulario:</b> En el Screen correspondiente (LoginScreen, RegisterScreen)", code_style))
    story.append(Paragraph("• <b>Errores de base de datos:</b> En los Repository con try-catch", code_style))
    story.append(Paragraph("• <b>Errores de autenticación:</b> AuthViewModel.kt (líneas 93-140)", code_style))
    story.append(Paragraph("• <b>Mensajes al usuario:</b> SnackbarHostState en MainActivity o cada Screen", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("P4: ¿Cómo se comunican las pantallas?", subheading_style))
    story.append(Paragraph("<b>R:</b> A través de:", normal_style))
    story.append(Paragraph("• <b>ViewModel compartido:</b> Para datos entre pantallas", normal_style))
    story.append(Paragraph("• <b>Callbacks (onBack, onClick):</b> Para navegación", normal_style))
    story.append(Paragraph("• <b>currentScreen variable:</b> En MainActivity.kt controla qué pantalla mostrar", normal_style))
    story.append(Paragraph("• <b>StateFlow:</b> Para observar cambios en tiempo real", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("P5: ¿Dónde están los productos?", subheading_style))
    story.append(Paragraph("<b>R:</b> Los productos están en:", normal_style))
    story.append(Paragraph("• <b>Archivo:</b> ProductoRepository.kt (línea 99-360)", code_style))
    story.append(Paragraph("• <b>Ruta:</b> app/src/main/java/com/levelup/gamer/repository/ProductoRepository.kt", code_style))
    story.append(Paragraph("• <b>Tipo:</b> Lista mutable de objetos Producto", normal_style))
    story.append(Paragraph("• <b>Cantidad:</b> 11 productos predefinidos", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("P6: ¿Cómo funciona el descuento DUOC?", subheading_style))
    story.append(Paragraph("<b>R:</b>", normal_style))
    story.append(Paragraph("• <b>Detección:</b> UserEntity.kt (línea 36-43) - función calcularDescuentoPorEmail", code_style))
    story.append(Paragraph("• <b>Condición:</b> Si el email termina en '@duocuc.cl' → 20% descuento", normal_style))
    story.append(Paragraph("• <b>Aplicación:</b> CartScreen.kt al calcular el total", normal_style))
    story.append(Paragraph("• <b>Visualización:</b> CartSummary muestra 'Descuento DuocUC: -$XXX'", normal_style))
    
    story.append(PageBreak())
    
    # PÁGINA FINAL - MAPA RÁPIDO
    story.append(Paragraph("MAPA RÁPIDO DE UBICACIONES", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    mapa_rapido = [
        ["Necesito encontrar...", "Archivo", "Línea Aprox."],
        ["Buscador de productos", "HomeScreen.kt", "72-93"],
        ["Botón favoritos", "ProductDetailScreen.kt", "90-110"],
        ["Carrito de compras", "CartScreen.kt", "Todo el archivo"],
        ["Detalle de boleta", "CartScreen.kt", "233-370"],
        ["Panel admin", "AdminScreen.kt", "Todo el archivo"],
        ["Sistema de puntos", "PedidoRepository.kt", "31-52"],
        ["Cerrar sesión", "ProfileScreen.kt", "466-520"],
        ["Login", "LoginScreen.kt", "Todo el archivo"],
        ["Registro", "RegisterScreen.kt", "Todo el archivo"],
        ["Productos", "ProductoRepository.kt", "99-360"],
        ["Base de datos", "AppDatabase.kt", "Todo el archivo"],
        ["Usuarios default", "AuthRepository.kt", "70-95"],
    ]
    
    mapa_table = Table(mapa_rapido, colWidths=[2.5*inch, 2.3*inch, 1.7*inch])
    mapa_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 10),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 9),
    ]))
    story.append(mapa_table)
    
    story.append(Spacer(1, 0.3*inch))
    story.append(Paragraph("CONSEJOS PARA LA PRESENTACIÓN", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    consejos = [
        "1. Tener Android Studio abierto con el proyecto cargado",
        "2. Conocer la ruta completa: C:\\Users\\Asgard\\Aplicacion_Mobile_RodDev",
        "3. Mostrar la app corriendo en el emulador o dispositivo",
        "4. Demostrar login con ambas cuentas (admin y usuario)",
        "5. Navegar por todas las pantallas principales",
        "6. Mostrar el código de una funcionalidad clave (favoritos o búsqueda)",
        "7. Explicar el patrón MVVM con un ejemplo visual",
        "8. Tener preparado abrir cualquier archivo rápidamente (Ctrl+Shift+N)",
    ]
    
    for consejo in consejos:
        story.append(Paragraph(consejo, normal_style))
    
    story.append(Spacer(1, 0.2*inch))
    story.append(Paragraph("¡ÉXITO EN TU PRESENTACIÓN!", title_style))
    
    # Generar PDF
    doc.build(story)
    print(f"✅ Guía generada exitosamente: {pdf_filename}")
    return pdf_filename

if __name__ == "__main__":
    crear_guia_presentacion()
