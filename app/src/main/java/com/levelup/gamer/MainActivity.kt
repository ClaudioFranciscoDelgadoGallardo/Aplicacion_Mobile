package com.levelup.gamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.levelup.gamer.data.UserPreferences
import com.levelup.gamer.repository.ProductoRepository
import com.levelup.gamer.repository.auth.AuthRepository
import com.levelup.gamer.repository.carrito.CarritoRepository
import com.levelup.gamer.repository.database.AppDatabase
import com.levelup.gamer.ui.navigation.MainDrawer
import com.levelup.gamer.ui.screens.*
import com.levelup.gamer.ui.theme.LevelUpGamerTheme
import com.levelup.gamer.viewmodel.AuthViewModel
import com.levelup.gamer.viewmodel.CartViewModel
import com.levelup.gamer.viewmodel.HomeViewModel
import com.levelup.gamer.viewmodel.CategoriesViewModel
import com.levelup.gamer.viewmodel.NewsViewModel
import com.levelup.gamer.viewmodel.ContactViewModel
import com.levelup.gamer.viewmodel.ProfileViewModel
import com.levelup.gamer.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    
    private lateinit var productoRepository: ProductoRepository
    private lateinit var carritoRepository: CarritoRepository
    private lateinit var authViewModel: AuthViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var productDetailViewModel: ProductDetailViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        productoRepository = ProductoRepository()
        
        val database = AppDatabase.getDatabase(applicationContext)
        carritoRepository = CarritoRepository(database.carritoDao())
        
        val userPreferences = UserPreferences(applicationContext)
        val authRepository = AuthRepository(database.userDao())
        authViewModel = AuthViewModel(authRepository, userPreferences)
        
        cartViewModel = CartViewModel(carritoRepository)
        homeViewModel = HomeViewModel()
        categoriesViewModel = CategoriesViewModel()
        newsViewModel = NewsViewModel()
        contactViewModel = ContactViewModel()
        profileViewModel = ProfileViewModel()
        productDetailViewModel = ProductDetailViewModel()
        
        setContent {
            LevelUpGamerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(
                        productoRepository = productoRepository,
                        carritoRepository = carritoRepository,
                        authViewModel = authViewModel,
                        cartViewModel = cartViewModel,
                        homeViewModel = homeViewModel,
                        categoriesViewModel = categoriesViewModel,
                        newsViewModel = newsViewModel,
                        contactViewModel = contactViewModel,
                        profileViewModel = profileViewModel,
                        productDetailViewModel = productDetailViewModel
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
    carritoRepository: CarritoRepository,
    authViewModel: AuthViewModel,
    cartViewModel: CartViewModel,
    homeViewModel: HomeViewModel,
    categoriesViewModel: CategoriesViewModel,
    newsViewModel: NewsViewModel,
    contactViewModel: ContactViewModel,
    profileViewModel: ProfileViewModel,
    productDetailViewModel: ProductDetailViewModel
) {
    var currentScreen by remember { mutableStateOf("inicio") }
    var selectedProducto by remember { mutableStateOf<com.levelup.gamer.model.Producto?>(null) }
    
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val authState by authViewModel.authState.collectAsState()
    val isUserLoggedIn = authState.currentUser != null
    
    val cantidadItems by carritoRepository.cantidadItems.collectAsState(initial = 0)

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
                isUserLoggedIn = isUserLoggedIn,
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
            Box(modifier = Modifier.padding(paddingValues)) {
                when (currentScreen) {
                    "inicio" -> {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onCartClick = {
                                currentScreen = "carrito"
                            },
                            onProductClick = { producto ->
                                selectedProducto = producto
                                currentScreen = "detalle_producto"
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
                            viewModel = cartViewModel,
                            onBackClick = {
                                currentScreen = "inicio"
                            },
                            onCheckoutSuccess = {
                                snackbarMessage = "¡Compra realizada con éxito!"
                                showSnackbar = true
                                currentScreen = "inicio"
                            }
                        )
                    }

                    "categorias" -> {
                        CategoriesScreen(
                            viewModel = categoriesViewModel,
                            onBack = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onCategoryClick = { categoria ->
                                homeViewModel.filterByCategory(categoria)
                                currentScreen = "inicio"
                                snackbarMessage = "Mostrando productos de $categoria"
                                showSnackbar = true
                            }
                        )
                    }

                    "noticias" -> {
                        NewsScreen(
                            viewModel = newsViewModel,
                            onBack = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }

                    "contacto" -> {
                        ContactScreen(
                            viewModel = contactViewModel,
                            onBack = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }

                    "login" -> {
                        if (isUserLoggedIn) {
                            currentScreen = "perfil"
                        } else {
                            LoginScreen(
                                authViewModel = authViewModel,
                                onBack = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                                onLoginSuccess = {
                                    currentScreen = "perfil"
                                    snackbarMessage = "¡Bienvenido!"
                                    showSnackbar = true
                                    scope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }

                    "perfil" -> {
                        if (isUserLoggedIn) {
                            ProfileScreen(
                                viewModel = profileViewModel,
                                onBack = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        } else {
                            currentScreen = "login"
                        }
                    }

                    "productos" -> {
                        CategoriesScreen(
                            viewModel = categoriesViewModel,
                            onBack = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onCategoryClick = { categoria ->
                                snackbarMessage = "Viendo productos de: $categoria"
                                showSnackbar = true
                            }
                        )
                    }

                    "configuracion" -> {
                        SettingsScreen(
                            isUserLoggedIn = isUserLoggedIn,
                            onBack = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onLogout = {
                                authViewModel.logout()
                                currentScreen = "inicio"
                                snackbarMessage = "Sesión cerrada"
                                showSnackbar = true
                            }
                        )
                    }

                    "detalle_producto" -> {
                        selectedProducto?.let { producto ->
                            LaunchedEffect(producto.codigo) {
                                productDetailViewModel.setProducto(producto)
                                productDetailViewModel.resetAddedToCartFlag()
                                productDetailViewModel.resetQuantity()
                            }
                            
                            ProductDetailScreen(
                                producto = producto,
                                viewModel = productDetailViewModel,
                                onBack = {
                                    currentScreen = "inicio"
                                    selectedProducto = null
                                    productDetailViewModel.resetAddedToCartFlag()
                                },
                                onAddToCart = {
                                    scope.launch {
                                        val quantity = productDetailViewModel.uiState.value.quantity
                                        carritoRepository.agregarProducto(producto, quantity)
                                        snackbarMessage = "${producto.nombre} (x$quantity) añadido al carrito"
                                        showSnackbar = true
                                    }
                                }
                            )
                        } ?: run {
                            currentScreen = "inicio"
                        }
                    }

                    else -> {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onCartClick = {
                                currentScreen = "carrito"
                            },
                            onProductClick = { producto ->
                                selectedProducto = producto
                                currentScreen = "detalle_producto"
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
                }
            }
        }
    }
}
