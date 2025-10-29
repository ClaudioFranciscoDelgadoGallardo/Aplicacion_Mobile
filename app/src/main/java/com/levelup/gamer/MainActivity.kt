package com.levelup.gamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.levelup.gamer.model.CarritoItem
import com.levelup.gamer.repository.ProductoRepository
import com.levelup.gamer.repository.carrito.CarritoRepository
import com.levelup.gamer.repository.database.AppDatabase
import com.levelup.gamer.ui.navigation.MainDrawer
import com.levelup.gamer.ui.screens.*
import com.levelup.gamer.ui.theme.LevelUpGamerTheme
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    
    private lateinit var productoRepository: ProductoRepository
    private lateinit var carritoRepository: CarritoRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        productoRepository = ProductoRepository()
        
        val database = AppDatabase.getDatabase(applicationContext)
        carritoRepository = CarritoRepository(database.carritoDao())
        
        setContent {
            LevelUpGamerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(
                        productoRepository = productoRepository,
                        carritoRepository = carritoRepository
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    productoRepository: ProductoRepository,
    carritoRepository: CarritoRepository
) {
    var currentScreen by remember { mutableStateOf("inicio") }
    
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val itemsCarrito by carritoRepository.itemsCarrito.collectAsState(initial = emptyList())
    val cantidadItems by carritoRepository.cantidadItems.collectAsState(initial = 0)
    val totalCarrito by carritoRepository.totalCarrito.collectAsState(initial = 0.0)
    
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    
    val snackbarHostState = remember { SnackbarHostState() }
    
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar(
                message = snackbarMessage,
                duration = SnackbarDuration.Short
            )
            showSnackbar = false
        }
    }
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainDrawer(
                currentRoute = currentScreen,
                onItemClick = { route ->
                    currentScreen = route
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            when (currentScreen) {
                "inicio" -> {
                    HomeScreen(
                        productos = productoRepository.obtenerProductosDestacados(),
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        onCartClick = {
                            currentScreen = "carrito"
                        },
                        onProductClick = { producto ->
                            snackbarMessage = "Próximamente: Detalles de ${producto.nombre}"
                            showSnackbar = true
                        },
                        onAddToCart = { producto ->
                            scope.launch {
                                carritoRepository.agregarProducto(producto)
                                snackbarMessage = "${producto.nombre} añadido al carrito"
                                showSnackbar = true
                            }
                        },
                        cartItemCount = cantidadItems
                    )
                }
                
                "carrito" -> {
                    CartScreen(
                        items = itemsCarrito,
                        total = totalCarrito ?: 0.0,
                        onBackClick = {
                            currentScreen = "inicio"
                        },
                        onIncrementClick = { item ->
                            scope.launch {
                                carritoRepository.incrementarCantidad(item)
                            }
                        },
                        onDecrementClick = { item ->
                            scope.launch {
                                carritoRepository.decrementarCantidad(item)
                            }
                        },
                        onRemoveClick = { item ->
                            scope.launch {
                                carritoRepository.eliminarItem(item)
                                snackbarMessage = "${item.nombre} eliminado del carrito"
                                showSnackbar = true
                            }
                        },
                        onCheckoutClick = {
                            scope.launch {
                                carritoRepository.vaciarCarrito()
                                snackbarMessage = "¡Compra realizada con éxito!"
                                showSnackbar = true
                                currentScreen = "inicio"
                            }
                        }
                    )
                }
                
                "categorias" -> {
                    PlaceholderScreen(
                        title = "Categorías",
                        message = "Próximamente: Explora productos por categoría",
                        onBackClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                "noticias" -> {
                    PlaceholderScreen(
                        title = "Noticias",
                        message = "Próximamente: Últimas noticias del mundo gamer",
                        onBackClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                "contacto" -> {
                    PlaceholderScreen(
                        title = "Contáctenos",
                        message = "Próximamente: Formulario de contacto",
                        onBackClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                "login" -> {
                    PlaceholderScreen(
                        title = "Iniciar Sesión",
                        message = "Próximamente: Sistema de autenticación",
                        onBackClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                "configuracion" -> {
                    PlaceholderScreen(
                        title = "Configuración",
                        message = "Próximamente: Ajustes de la aplicación",
                        onBackClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderScreen(
    title: String,
    message: String,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            androidx.compose.material.icons.Icons.Default.Menu,
                            contentDescription = "Menú"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            androidx.compose.foundation.layout.Column(
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                modifier = androidx.compose.ui.Modifier.padding(32.dp)
            ) {
                Icon(
                    androidx.compose.material.icons.Icons.Default.Construction,
                    contentDescription = "En construcción",
                    modifier = androidx.compose.ui.Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                androidx.compose.foundation.layout.Spacer(
                    modifier = androidx.compose.ui.Modifier.height(16.dp)
                )
                Text(
                    message,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}
