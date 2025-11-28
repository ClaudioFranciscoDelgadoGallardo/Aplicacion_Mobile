package com.levelup.gamer.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.levelup.gamer.model.Producto
import com.levelup.gamer.ui.theme.NeonGreen
import com.levelup.gamer.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onMenuClick: () -> Unit,
    onCartClick: () -> Unit,
    onProductClick: (Producto) -> Unit,
    onAddToCart: (Producto) -> Unit,
    cartItemCount: Int = 0
) {
    val uiState by viewModel.uiState.collectAsState()
    val productosFiltrados = viewModel.getFilteredProducts()
    
    Scaffold(
        topBar = {
            if (uiState.isSearchActive) {
                SearchTopBar(
                    searchQuery = uiState.searchQuery,
                    onSearchQueryChange = { viewModel.updateSearchQuery(it) },
                    onBackClick = { viewModel.setSearchActive(false) },
                    onCartClick = onCartClick,
                    cartItemCount = cartItemCount
                )
            } else {
                NormalTopBar(
                    onMenuClick = onMenuClick,
                    onCartClick = onCartClick,
                    onSearchClick = { viewModel.setSearchActive(true) },
                    cartItemCount = cartItemCount
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (uiState.selectedCategory != null) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Categoría: ${uiState.selectedCategory}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    AssistChip(
                        onClick = { viewModel.filterByCategory(null) },
                        label = { Text("Limpiar filtro") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    )
                }
            }
            
            if (uiState.searchQuery.isNotEmpty()) {
                Text(
                    "Resultados para \"${uiState.searchQuery}\"",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            } else if (uiState.selectedCategory == null) {
                Text(
                    "Productos Destacados",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            if (uiState.searchQuery.isNotEmpty() && productosFiltrados.isEmpty()) {
                EmptySearchResults()
            } else {
                ProductosGrid(
                    productos = productosFiltrados,
                    onProductClick = onProductClick,
                    onAddToCart = onAddToCart
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopBar(
    onMenuClick: () -> Unit,
    onCartClick: () -> Unit,
    onSearchClick: () -> Unit,
    cartItemCount: Int
) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.SportsEsports,
                    contentDescription = "Logo",
                    tint = NeonGreen,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Level-Up Gamer",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            }
            
            Box(modifier = Modifier.padding(end = 8.dp)) {
                IconButton(onClick = onCartClick) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Carrito"
                    )
                }
                com.levelup.gamer.ui.components.AnimatedCartBadge(
                    count = cartItemCount,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 4.dp, y = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    cartItemCount: Int
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    
    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { Text("Buscar productos...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                    }
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        actions = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(Icons.Default.Close, contentDescription = "Limpiar")
                }
            }
            
            Box(modifier = Modifier.padding(end = 8.dp)) {
                IconButton(onClick = onCartClick) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                }
                com.levelup.gamer.ui.components.AnimatedCartBadge(
                    count = cartItemCount,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 4.dp, y = 8.dp)
                )
            }
        }
    )
}

@Composable
fun ProductosGrid(
    productos: List<Producto>,
    onProductClick: (Producto) -> Unit,
    onAddToCart: (Producto) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(productos) { index, producto ->
            com.levelup.gamer.ui.components.AnimatedProductCard(
                producto = producto,
                onClick = { onProductClick(producto) },
                onAddToCart = { onAddToCart(producto) },
                index = index
            )
        }
    }
}

@Composable
fun ProductoCard(
    producto: Producto,
    onClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                val context = LocalContext.current
                val imageResource = com.levelup.gamer.utils.ImageUtils.getDrawableResourceId(
                    context,
                    producto.imagenUrl
                )
                
                if (imageResource != 0) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageResource)
                            .crossfade(true)
                            .build(),
                        contentDescription = producto.nombre,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = when (producto.categoria) {
                            "Consolas" -> Icons.Default.Gamepad
                            "Juegos" -> Icons.Default.SportsEsports
                            "Accesorios" -> Icons.Default.Headset
                            "PC Gaming" -> Icons.Default.Computer
                            else -> Icons.Default.Image
                        },
                        contentDescription = producto.nombre,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(48.dp)
                    )
                }
                
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = NeonGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            producto.puntuacion,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
            }
            
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    producto.nombre,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    producto.precio,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    producto.descripcionCorta,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        Icons.Default.AddShoppingCart,
                        contentDescription = "Añadir",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        "Añadir",
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun EmptySearchResults() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.SearchOff,
                contentDescription = "Sin resultados",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "No se encontraron productos",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "Intenta con otros términos",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
