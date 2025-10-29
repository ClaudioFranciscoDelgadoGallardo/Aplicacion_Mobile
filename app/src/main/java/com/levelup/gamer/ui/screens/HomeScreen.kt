package com.levelup.gamer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.levelup.gamer.model.Producto
import com.levelup.gamer.ui.theme.NeonGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    productos: List<Producto>,
    onMenuClick: () -> Unit,
    onCartClick: () -> Unit,
    onProductClick: (Producto) -> Unit,
    onAddToCart: (Producto) -> Unit,
    cartItemCount: Int = 0
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    
    val productosFiltrados = remember(searchQuery, productos) {
        if (searchQuery.isEmpty()) {
            productos
        } else {
            productos.filter { producto ->
                producto.nombre.contains(searchQuery, ignoreCase = true) ||
                producto.descripcionCorta.contains(searchQuery, ignoreCase = true) ||
                producto.categoria.contains(searchQuery, ignoreCase = true)
            }
        }
    }
    
    Scaffold(
        topBar = {
            if (isSearchActive) {
                SearchTopBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    onBackClick = {
                        isSearchActive = false
                        searchQuery = ""
                    },
                    onCartClick = onCartClick,
                    cartItemCount = cartItemCount
                )
            } else {
                NormalTopBar(
                    onMenuClick = onMenuClick,
                    onCartClick = onCartClick,
                    onSearchClick = { isSearchActive = true },
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
            if (searchQuery.isNotEmpty()) {
                Text(
                    "🔍 Resultados para \"$searchQuery\"",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                Text(
                    "⭐ Productos Destacados",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            if (searchQuery.isNotEmpty() && productosFiltrados.isEmpty()) {
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
                    imageVector = Icons.Default.Videogame,
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
                if (cartItemCount > 0) {
                    Badge(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 4.dp, y = 8.dp),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            if (cartItemCount > 9) "9+" else cartItemCount.toString(),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
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
    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
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
                singleLine = true
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
                if (cartItemCount > 0) {
                    Badge(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 4.dp, y = 8.dp),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            if (cartItemCount > 9) "9+" else cartItemCount.toString(),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
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
        items(productos) { producto ->
            ProductoCard(
                producto = producto,
                onClick = { onProductClick(producto) },
                onAddToCart = { onAddToCart(producto) }
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
