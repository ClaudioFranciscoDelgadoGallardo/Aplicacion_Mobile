#!/usr/bin/env python
# -*- coding: utf-8 -*-
from reportlab.lib.pagesizes import letter
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak, Table, TableStyle
from reportlab.lib.enums import TA_LEFT, TA_CENTER, TA_JUSTIFY
from reportlab.lib import colors
from datetime import datetime

def crear_analisis_comparativo():
    pdf_filename = "Analisis_Comparativo_Profesor_vs_TuApp.pdf"
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
    
    good_style = ParagraphStyle(
        'Good',
        parent=styles['Normal'],
        fontSize=10,
        textColor=colors.green,
        leftIndent=10
    )
    
    bad_style = ParagraphStyle(
        'Bad',
        parent=styles['Normal'],
        fontSize=10,
        textColor=colors.red,
        leftIndent=10
    )
    
    # PORTADA
    story.append(Spacer(1, 1.5*inch))
    story.append(Paragraph("ANÁLISIS COMPARATIVO", title_style))
    story.append(Paragraph("App Profesor vs Tu Aplicación", heading_style))
    story.append(Spacer(1, 0.5*inch))
    story.append(Paragraph("Diferencias Arquitectónicas y Plan de Adaptación", normal_style))
    story.append(Paragraph("Manteniendo tu diseño visual intacto", normal_style))
    story.append(Spacer(1, 0.3*inch))
    story.append(Paragraph(f"Fecha: {datetime.now().strftime('%d de diciembre de 2025')}", normal_style))
    story.append(PageBreak())
    
    # TABLA COMPARATIVA GENERAL
    story.append(Paragraph("1. COMPARACIÓN GENERAL DE ARQUITECTURA", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    comparacion_data = [
        ["Aspecto", "App Profesor", "Tu App (Level-Up Gamer)", "Acción"],
        ["Navegación", "NavHost con sealed class Routes", "String-based con when/currentScreen", "✅ Adoptar sealed class"],
        ["MainActivity", "Simple, delega a AppNavHost", "Compleja, maneja toda la lógica", "✅ Simplificar"],
        ["Autenticación", "Firebase Authentication", "Room Database local", "⚠️ Mantener Room"],
        ["ViewModels", "StateFlow + data class UiState", "MutableStateFlow dispersos", "✅ Consolidar en UiState"],
        ["Repository", "AuthRepository + DataSource", "Múltiples repositories", "✅ Mismo patrón"],
        ["Pantallas", "Separadas en packages (ui/login, ui/register)", "Agrupadas en ui/screens", "✅ Reorganizar"],
        ["Bottom Navigation", "Sí (BottomBar con sealed class)", "No", "⚠️ Opcional"],
        ["Drawer", "No", "Sí (ModalNavigationDrawer)", "✅ Mantener"],
    ]
    
    comp_table = Table(comparacion_data, colWidths=[1.3*inch, 1.6*inch, 1.8*inch, 1.3*inch])
    comp_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.HexColor('#39FF14')),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 8),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.white),
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('FONTSIZE', (0, 1), (-1, -1), 7),
    ]))
    story.append(comp_table)
    
    story.append(PageBreak())
    
    # DIFERENCIAS CLAVE
    story.append(Paragraph("2. DIFERENCIAS CLAVE A ADAPTAR", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("A. NAVEGACIÓN - SEALED CLASS vs STRING", subheading_style))
    story.append(Paragraph("<b>App Profesor (Recomendado):</b>", normal_style))
    story.append(Paragraph("sealed class Route(val path: String) {", code_style))
    story.append(Paragraph("    data object HomeRoot : Route(\"homeRoot\")", code_style))
    story.append(Paragraph("    data object Login : Route(\"login\")", code_style))
    story.append(Paragraph("    data object Principal : Route(\"principal\")", code_style))
    story.append(Paragraph("}", code_style))
    story.append(Paragraph("nav.navigate(Route.Login.path)", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>Tu App (Actual):</b>", normal_style))
    story.append(Paragraph("var currentScreen by remember { mutableStateOf(\"inicio\") }", code_style))
    story.append(Paragraph("when(currentScreen) {", code_style))
    story.append(Paragraph("    \"inicio\" -> HomeScreen(...)", code_style))
    story.append(Paragraph("    \"login\" -> LoginScreen(...)", code_style))
    story.append(Paragraph("}", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>✅ Ventajas de sealed class:</b>", normal_style))
    story.append(Paragraph("• Type-safety: Compilador detecta errores", good_style))
    story.append(Paragraph("• Autocompletado en IDE", good_style))
    story.append(Paragraph("• Refactoring más seguro", good_style))
    story.append(Paragraph("• Soporta parámetros de navegación fácilmente", good_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>❌ Problemas de strings:</b>", normal_style))
    story.append(Paragraph("• Errores en tiempo de ejecución (typos)", bad_style))
    story.append(Paragraph("• Difícil de mantener", bad_style))
    story.append(Paragraph("• No hay verificación del compilador", bad_style))
    
    story.append(PageBreak())
    
    # VIEWMODEL Y UISTATE
    story.append(Paragraph("B. VIEWMODEL CON UISTATE CONSOLIDADO", subheading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>App Profesor (Patrón MVVM puro):</b>", normal_style))
    story.append(Paragraph("data class LoginUiState(", code_style))
    story.append(Paragraph("    val email: String = \"\",", code_style))
    story.append(Paragraph("    val password: String = \"\",", code_style))
    story.append(Paragraph("    val loading: Boolean = false,", code_style))
    story.append(Paragraph("    val error: String? = null,", code_style))
    story.append(Paragraph("    val loggedIn: Boolean = false,", code_style))
    story.append(Paragraph("    val user: User? = null", code_style))
    story.append(Paragraph(")", code_style))
    story.append(Spacer(1, 0.05*inch))
    story.append(Paragraph("class LoginViewModel : ViewModel() {", code_style))
    story.append(Paragraph("    private val _ui = MutableStateFlow(LoginUiState())", code_style))
    story.append(Paragraph("    val ui: StateFlow<LoginUiState> = _ui", code_style))
    story.append(Paragraph("", code_style))
    story.append(Paragraph("    fun onEmailChange(v: String) = _ui.update { it.copy(email = v) }", code_style))
    story.append(Paragraph("    fun onPasswordChange(v: String) = _ui.update { it.copy(password = v) }", code_style))
    story.append(Paragraph("}", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>Tu App (Actual):</b>", normal_style))
    story.append(Paragraph("class AuthViewModel : ViewModel() {", code_style))
    story.append(Paragraph("    private val _email = MutableStateFlow(\"\")", code_style))
    story.append(Paragraph("    val email: StateFlow<String> = _email", code_style))
    story.append(Paragraph("    ", code_style))
    story.append(Paragraph("    private val _password = MutableStateFlow(\"\")", code_style))
    story.append(Paragraph("    val password: StateFlow<String> = _password", code_style))
    story.append(Paragraph("    ", code_style))
    story.append(Paragraph("    private val _isLoading = MutableStateFlow(false)", code_style))
    story.append(Paragraph("    val isLoading: StateFlow<Boolean> = _isLoading", code_style))
    story.append(Paragraph("    ", code_style))
    story.append(Paragraph("    private val _error = MutableStateFlow<String?>(null)", code_style))
    story.append(Paragraph("    val error: StateFlow<String?> = _error", code_style))
    story.append(Paragraph("    // ... muchos más StateFlows individuales", code_style))
    story.append(Paragraph("}", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>✅ Beneficios de UiState consolidado:</b>", normal_style))
    story.append(Paragraph("• Un solo punto de verdad (Single Source of Truth)", good_style))
    story.append(Paragraph("• Actualizaciones atómicas con .copy()", good_style))
    story.append(Paragraph("• Más fácil de testear", good_style))
    story.append(Paragraph("• Menos boilerplate en el Screen", good_style))
    story.append(Paragraph("• Mejor rendimiento (menos recomposiciones)", good_style))
    
    story.append(PageBreak())
    
    # ESTRUCTURA DE ARCHIVOS
    story.append(Paragraph("C. ORGANIZACIÓN DE ARCHIVOS", subheading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>App Profesor:</b>", normal_style))
    story.append(Paragraph("ui/", code_style))
    story.append(Paragraph("  ├── app/", code_style))
    story.append(Paragraph("  │   ├── AppNavHost.kt", code_style))
    story.append(Paragraph("  │   └── Routes.kt", code_style))
    story.append(Paragraph("  ├── login/", code_style))
    story.append(Paragraph("  │   ├── LoginScreen.kt", code_style))
    story.append(Paragraph("  │   └── LoginViewModel.kt", code_style))
    story.append(Paragraph("  ├── register/", code_style))
    story.append(Paragraph("  │   ├── RegistrarseScreen.kt", code_style))
    story.append(Paragraph("  │   └── RegistrarseViewModel.kt", code_style))
    story.append(Paragraph("  ├── principal/", code_style))
    story.append(Paragraph("  │   ├── PrincipalScreen.kt", code_style))
    story.append(Paragraph("  │   ├── PrincipalViewModel.kt", code_style))
    story.append(Paragraph("  │   └── components/", code_style))
    story.append(Paragraph("  └── theme/", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>Tu App (Actual):</b>", normal_style))
    story.append(Paragraph("ui/", code_style))
    story.append(Paragraph("  ├── screens/", code_style))
    story.append(Paragraph("  │   ├── HomeScreen.kt", code_style))
    story.append(Paragraph("  │   ├── LoginScreen.kt", code_style))
    story.append(Paragraph("  │   ├── RegisterScreen.kt", code_style))
    story.append(Paragraph("  │   ├── ProfileScreen.kt", code_style))
    story.append(Paragraph("  │   └── ... (todas las screens juntas)", code_style))
    story.append(Paragraph("  └── navigation/", code_style))
    story.append(Paragraph("      └── MainDrawer.kt", code_style))
    story.append(Paragraph("viewmodel/ (en raíz)", code_style))
    story.append(Paragraph("  ├── AuthViewModel.kt", code_style))
    story.append(Paragraph("  ├── HomeViewModel.kt", code_style))
    story.append(Paragraph("  └── ...", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("<b>✅ Ventajas de separación por feature:</b>", normal_style))
    story.append(Paragraph("• Cohesión: Todo lo de Login junto", good_style))
    story.append(Paragraph("• Escalabilidad: Fácil agregar features", good_style))
    story.append(Paragraph("• Claridad: Se entiende la estructura", good_style))
    
    story.append(PageBreak())
    
    # PLAN DE ADAPTACIÓN
    story.append(Paragraph("3. PLAN DE ADAPTACIÓN DETALLADO", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("FASE 1: Crear Routes.kt (sealed class)", subheading_style))
    story.append(Paragraph("Ruta: app/src/main/java/com/levelup/gamer/ui/app/Routes.kt", code_style))
    story.append(Paragraph("", normal_style))
    story.append(Paragraph("sealed class Route(val path: String) {", code_style))
    story.append(Paragraph("    data object Inicio : Route(\"inicio\")", code_style))
    story.append(Paragraph("    data object Login : Route(\"login\")", code_style))
    story.append(Paragraph("    data object Register : Route(\"register\")", code_style))
    story.append(Paragraph("    data object Home : Route(\"home\")", code_style))
    story.append(Paragraph("    data object Profile : Route(\"profile\")", code_style))
    story.append(Paragraph("    data object Admin : Route(\"admin\")", code_style))
    story.append(Paragraph("    data object Cart : Route(\"cart\")", code_style))
    story.append(Paragraph("    data object ProductDetail : Route(\"productDetail/{codigo}\") {", code_style))
    story.append(Paragraph("        fun createRoute(codigo: String) = \"productDetail/$codigo\"", code_style))
    story.append(Paragraph("    }", code_style))
    story.append(Paragraph("    data object Pedidos : Route(\"pedidos\")", code_style))
    story.append(Paragraph("    data object Categories : Route(\"categories\")", code_style))
    story.append(Paragraph("    data object News : Route(\"news\")", code_style))
    story.append(Paragraph("    data object Contact : Route(\"contact\")", code_style))
    story.append(Paragraph("    data object Settings : Route(\"settings\")", code_style))
    story.append(Paragraph("}", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("FASE 2: Crear AppNavHost.kt", subheading_style))
    story.append(Paragraph("Ruta: app/src/main/java/com/levelup/gamer/ui/app/AppNavHost.kt", code_style))
    story.append(Paragraph("", normal_style))
    story.append(Paragraph("• Mover toda la lógica de navegación desde MainActivity", normal_style))
    story.append(Paragraph("• Usar NavHost con NavController", normal_style))
    story.append(Paragraph("• Cada composable() recibe callbacks simples", normal_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("FASE 3: Consolidar ViewModels en UiState", subheading_style))
    story.append(Paragraph("Para cada ViewModel existente:", normal_style))
    story.append(Paragraph("1. Crear data class XxxUiState con todos los estados", normal_style))
    story.append(Paragraph("2. Reemplazar múltiples StateFlows por uno solo", normal_style))
    story.append(Paragraph("3. Usar _ui.update { it.copy(...) } para cambios", normal_style))
    story.append(Paragraph("", normal_style))
    story.append(Paragraph("Ejemplo HomeViewModel:", normal_style))
    story.append(Paragraph("data class HomeUiState(", code_style))
    story.append(Paragraph("    val productos: List<Producto> = emptyList(),", code_style))
    story.append(Paragraph("    val productosFiltrados: List<Producto> = emptyList(),", code_style))
    story.append(Paragraph("    val searchQuery: String = \"\",", code_style))
    story.append(Paragraph("    val isLoading: Boolean = false,", code_style))
    story.append(Paragraph("    val error: String? = null", code_style))
    story.append(Paragraph(")", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("FASE 4: Reorganizar estructura de carpetas", subheading_style))
    story.append(Paragraph("Mover archivos a estructura por feature:", normal_style))
    story.append(Paragraph("ui/", code_style))
    story.append(Paragraph("  ├── app/", code_style))
    story.append(Paragraph("  │   ├── AppNavHost.kt (NUEVO)", code_style))
    story.append(Paragraph("  │   └── Routes.kt (NUEVO)", code_style))
    story.append(Paragraph("  ├── home/", code_style))
    story.append(Paragraph("  │   ├── HomeScreen.kt", code_style))
    story.append(Paragraph("  │   └── HomeViewModel.kt", code_style))
    story.append(Paragraph("  ├── auth/", code_style))
    story.append(Paragraph("  │   ├── LoginScreen.kt", code_style))
    story.append(Paragraph("  │   ├── RegisterScreen.kt", code_style))
    story.append(Paragraph("  │   └── AuthViewModel.kt", code_style))
    story.append(Paragraph("  ├── profile/", code_style))
    story.append(Paragraph("  │   ├── ProfileScreen.kt", code_style))
    story.append(Paragraph("  │   └── ProfileViewModel.kt", code_style))
    story.append(Paragraph("  ├── admin/", code_style))
    story.append(Paragraph("  │   ├── AdminScreen.kt", code_style))
    story.append(Paragraph("  │   └── AdminViewModel.kt", code_style))
    story.append(Paragraph("  └── ... (resto igual)", code_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("FASE 5: Simplificar MainActivity", subheading_style))
    story.append(Paragraph("Nueva MainActivity (simple como la del profesor):", normal_style))
    story.append(Paragraph("class MainActivity : ComponentActivity() {", code_style))
    story.append(Paragraph("    override fun onCreate(savedInstanceState: Bundle?) {", code_style))
    story.append(Paragraph("        super.onCreate(savedInstanceState)", code_style))
    story.append(Paragraph("        enableEdgeToEdge()", code_style))
    story.append(Paragraph("        setContent {", code_style))
    story.append(Paragraph("            LevelUpGamerTheme {", code_style))
    story.append(Paragraph("                AppNavHost()", code_style))
    story.append(Paragraph("            }", code_style))
    story.append(Paragraph("        }", code_style))
    story.append(Paragraph("    }", code_style))
    story.append(Paragraph("}", code_style))
    
    story.append(PageBreak())
    
    # LO QUE NO DEBES CAMBIAR
    story.append(Paragraph("4. LO QUE NO DEBES CAMBIAR (MANTENER INTACTO)", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("✅ CONSERVAR SIN MODIFICACIONES:", subheading_style))
    story.append(Paragraph("• <b>Colores:</b> ui/theme/Color.kt (tu paleta verde neón #39FF14)", good_style))
    story.append(Paragraph("• <b>Imágenes:</b> Todos los drawables en res/drawable/", good_style))
    story.append(Paragraph("• <b>Logo y branding:</b> Level-Up Gamer", good_style))
    story.append(Paragraph("• <b>Drawer lateral:</b> MainDrawer.kt (el profesor no lo tiene)", good_style))
    story.append(Paragraph("• <b>Room Database:</b> Mantener Room en lugar de Firebase", good_style))
    story.append(Paragraph("• <b>ProductoRepository:</b> Lista de productos hardcoded", good_style))
    story.append(Paragraph("• <b>Sistema de puntos:</b> Lógica de 5% y descuento DUOC", good_style))
    story.append(Paragraph("• <b>Panel Admin:</b> AdminScreen completo", good_style))
    story.append(Paragraph("• <b>Favoritos:</b> FavoritosRepository + FavoritoDao", good_style))
    story.append(Paragraph("• <b>Carrito:</b> CarritoRepository + CartScreen con detalle boleta", good_style))
    story.append(Paragraph("• <b>Diseño visual:</b> Todos los composables de UI", good_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("⚠️ ADAPTAR SOLO LA ESTRUCTURA:", subheading_style))
    story.append(Paragraph("• Cambiar navegación string → sealed class Routes", normal_style))
    story.append(Paragraph("• Consolidar StateFlows en data class UiState", normal_style))
    story.append(Paragraph("• Reorganizar carpetas por feature", normal_style))
    story.append(Paragraph("• Simplificar MainActivity", normal_style))
    story.append(Paragraph("• Separar navegación en AppNavHost.kt", normal_style))
    
    story.append(PageBreak())
    
    # RESUMEN EJECUTIVO
    story.append(Paragraph("5. RESUMEN EJECUTIVO", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Diferencias Principales:", subheading_style))
    
    diferencias_table = [
        ["Concepto", "Profesor", "Tu App", "Prioridad"],
        ["Navegación", "NavHost + sealed Routes", "String + when", "🔴 ALTA"],
        ["UiState", "Consolidado en data class", "StateFlows dispersos", "🔴 ALTA"],
        ["MainActivity", "Delega a NavHost", "Controla todo", "🟡 MEDIA"],
        ["Organización", "Por feature (ui/login/)", "Por tipo (ui/screens/)", "🟡 MEDIA"],
        ["Autenticación", "Firebase", "Room local", "🟢 BAJA - Mantener"],
        ["Backend", "Firebase", "Microservicios", "🟢 BAJA - Mantener"],
    ]
    
    dif_table = Table(diferencias_table, colWidths=[1.5*inch, 1.5*inch, 1.6*inch, 1.4*inch])
    dif_table.setStyle(TableStyle([
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
    story.append(dif_table)
    story.append(Spacer(1, 0.2*inch))
    
    story.append(Paragraph("Orden de Implementación Recomendado:", subheading_style))
    story.append(Paragraph("1️⃣ Crear Routes.kt con sealed class (15 min)", normal_style))
    story.append(Paragraph("2️⃣ Crear AppNavHost.kt básico (30 min)", normal_style))
    story.append(Paragraph("3️⃣ Migrar MainActivity a nueva estructura (15 min)", normal_style))
    story.append(Paragraph("4️⃣ Consolidar AuthViewModel con UiState (45 min)", normal_style))
    story.append(Paragraph("5️⃣ Consolidar HomeViewModel con UiState (30 min)", normal_style))
    story.append(Paragraph("6️⃣ Consolidar resto de ViewModels (2 horas)", normal_style))
    story.append(Paragraph("7️⃣ Reorganizar carpetas por feature (1 hora)", normal_style))
    story.append(Paragraph("8️⃣ Testing completo (1 hora)", normal_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("⏱️ Tiempo Total Estimado: 6-7 horas", subheading_style))
    story.append(Spacer(1, 0.2*inch))
    
    story.append(Paragraph("Conclusión:", subheading_style))
    story.append(Paragraph(
        "Tu aplicación tiene una <b>lógica de negocio sólida</b> y un <b>diseño visual profesional</b>. "
        "La adaptación consiste en mejorar la <b>arquitectura y organización del código</b> "
        "siguiendo las mejores prácticas que usa el profesor, SIN tocar la funcionalidad ni el diseño. "
        "Es una <b>refactorización estructural</b>, no un rediseño.",
        normal_style
    ))
    
    story.append(PageBreak())
    
    # CHECKLIST FINAL
    story.append(Paragraph("6. CHECKLIST DE ADAPTACIÓN", heading_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Antes de empezar:", subheading_style))
    story.append(Paragraph("□ Hacer commit de todo el código actual", normal_style))
    story.append(Paragraph("□ Crear branch nueva: git checkout -b refactor-arquitectura", code_style))
    story.append(Paragraph("□ Backup completo del proyecto", normal_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Durante la adaptación:", subheading_style))
    story.append(Paragraph("□ Crear Routes.kt", normal_style))
    story.append(Paragraph("□ Crear AppNavHost.kt", normal_style))
    story.append(Paragraph("□ Migrar MainActivity", normal_style))
    story.append(Paragraph("□ Crear XXXUiState para cada ViewModel", normal_style))
    story.append(Paragraph("□ Actualizar Screens para usar UiState", normal_style))
    story.append(Paragraph("□ Reorganizar carpetas ui/ por feature", normal_style))
    story.append(Paragraph("□ Actualizar imports en todos los archivos", normal_style))
    story.append(Paragraph("□ Probar compilación: ./gradlew clean build", code_style))
    story.append(Spacer(1, 0.1*inch))
    
    story.append(Paragraph("Testing final:", subheading_style))
    story.append(Paragraph("□ Login con credenciales demo", normal_style))
    story.append(Paragraph("□ Navegación entre todas las pantallas", normal_style))
    story.append(Paragraph("□ Búsqueda de productos", normal_style))
    story.append(Paragraph("□ Agregar al carrito", normal_style))
    story.append(Paragraph("□ Crear pedido", normal_style))
    story.append(Paragraph("□ Panel admin (si admin)", normal_style))
    story.append(Paragraph("□ Favoritos", normal_style))
    story.append(Paragraph("□ Perfil de usuario", normal_style))
    story.append(Paragraph("□ Cerrar sesión", normal_style))
    story.append(Spacer(1, 0.15*inch))
    
    story.append(Paragraph("Si todo funciona:", subheading_style))
    story.append(Paragraph("git add .", code_style))
    story.append(Paragraph("git commit -m \"Refactor: Arquitectura MVVM siguiendo patrón del profesor\"", code_style))
    story.append(Paragraph("git push origin refactor-arquitectura", code_style))
    story.append(Spacer(1, 0.3*inch))
    
    story.append(Paragraph("¡ARQUITECTURA ADAPTADA CON ÉXITO!", title_style))
    
    # Generar PDF
    doc.build(story)
    print(f"✅ Análisis comparativo generado: {pdf_filename}")
    return pdf_filename

if __name__ == "__main__":
    crear_analisis_comparativo()
