package com.levelup.gamer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levelup.gamer.model.Producto
import com.levelup.gamer.ui.theme.NeonGreen
import com.levelup.gamer.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    viewModel: AdminViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddProductDialog by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Panel de Administrador",
                        fontWeight = FontWeight.Bold,
                        color = NeonGreen
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showAddProductDialog = true }) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Agregar Producto",
                            tint = NeonGreen
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tabs para filtrar
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = NeonGreen
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Todos") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Bajo Stock") }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Sin Stock") }
                )
            }

            // Lista de productos
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = NeonGreen)
                }
            } else {
                val filteredProducts = when (selectedTab) {
                    1 -> uiState.productos.filter { 
                        val stock = it.stock.toIntOrNull() ?: 0
                        stock in 1..10 
                    }
                    2 -> uiState.productos.filter { 
                        val stock = it.stock.toIntOrNull() ?: 0
                        stock == 0 
                    }
                    else -> uiState.productos
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredProducts) { producto ->
                        ProductoAdminCard(
                            producto = producto,
                            onAddStock = { viewModel.agregarStock(producto.codigo, 1) },
                            onRemoveStock = { viewModel.quitarStock(producto.codigo, 1) },
                            onEditStock = { cantidad -> viewModel.actualizarStock(producto.codigo, cantidad) },
                            onDelete = { viewModel.eliminarProducto(producto.codigo) }
                        )
                    }

                    if (filteredProducts.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = when (selectedTab) {
                                        1 -> "No hay productos con bajo stock"
                                        2 -> "No hay productos sin stock"
                                        else -> "No hay productos disponibles"
                                    },
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Diálogo para agregar producto
    if (showAddProductDialog) {
        AddProductDialog(
            onDismiss = { showAddProductDialog = false },
            onConfirm = { producto ->
                viewModel.agregarProducto(producto)
                showAddProductDialog = false
            }
        )
    }
}

@Composable
fun ProductoAdminCard(
    producto: Producto,
    onAddStock: () -> Unit,
    onRemoveStock: () -> Unit,
    onEditStock: (Int) -> Unit,
    onDelete: () -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Encabezado con nombre y acciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = producto.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Código: ${producto.codigo}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Información del producto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Precio",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "$${producto.precio}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = NeonGreen
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Categoría",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = producto.categoria,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Control de stock
            val stockActual = producto.stock.toIntOrNull() ?: 0
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Stock disponible",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Botón quitar
                    IconButton(
                        onClick = onRemoveStock,
                        enabled = stockActual > 0
                    ) {
                        Icon(
                            Icons.Default.Remove,
                            contentDescription = "Quitar stock",
                            tint = if (stockActual > 0) Color.Red else Color.Gray
                        )
                    }

                    // Cantidad actual (clickable para editar)
                    Surface(
                        onClick = { showEditDialog = true },
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                when {
                                    stockActual == 0 -> Color.Red.copy(alpha = 0.2f)
                                    stockActual <= 10 -> Color.Yellow.copy(alpha = 0.2f)
                                    else -> NeonGreen.copy(alpha = 0.2f)
                                }
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "$stockActual",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                stockActual == 0 -> Color.Red
                                stockActual <= 10 -> Color(0xFFFFA000)
                                else -> NeonGreen
                            },
                            textAlign = TextAlign.Center,
                            modifier = Modifier.background(Color.Transparent)
                        )
                    }

                    // Botón agregar
                    IconButton(onClick = onAddStock) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Agregar stock",
                            tint = NeonGreen
                        )
                    }
                }
            }
        }
    }

    // Diálogo para editar stock manualmente
    if (showEditDialog) {
        EditStockDialog(
            currentStock = producto.stock.toIntOrNull() ?: 0,
            productName = producto.nombre,
            onDismiss = { showEditDialog = false },
            onConfirm = { newStock ->
                onEditStock(newStock)
                showEditDialog = false
            }
        )
    }

    // Diálogo de confirmación para eliminar
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Producto") },
            text = { Text("¿Estás seguro de que deseas eliminar '${producto.nombre}'? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDeleteDialog = false
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun EditStockDialog(
    currentStock: Int,
    productName: String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var stockText by remember { mutableStateOf(currentStock.toString()) }
    var error by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Stock") },
        text = {
            Column {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = stockText,
                    onValueChange = {
                        stockText = it
                        error = it.toIntOrNull() == null || it.toIntOrNull()!! < 0
                    },
                    label = { Text("Cantidad") },
                    isError = error,
                    supportingText = if (error) {
                        { Text("Ingresa un número válido (0 o mayor)") }
                    } else null,
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newStock = stockText.toIntOrNull()
                    if (newStock != null && newStock >= 0) {
                        onConfirm(newStock)
                    }
                },
                enabled = !error && stockText.isNotEmpty()
            ) {
                Text("Guardar", color = NeonGreen)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun AddProductDialog(
    onDismiss: () -> Unit,
    onConfirm: (Producto) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var descripcionCorta by remember { mutableStateOf("") }
    var descripcionLarga by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    var errorNombre by remember { mutableStateOf(false) }
    var errorCodigo by remember { mutableStateOf(false) }
    var errorPrecio by remember { mutableStateOf(false) }
    var errorStock by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { 
            Text(
                "Agregar Nuevo Producto",
                fontWeight = FontWeight.Bold,
                color = NeonGreen
            ) 
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            errorNombre = it.isBlank()
                        },
                        label = { Text("Nombre *") },
                        isError = errorNombre,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = codigo,
                        onValueChange = {
                            codigo = it
                            errorCodigo = it.isBlank()
                        },
                        label = { Text("Código *") },
                        isError = errorCodigo,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = precio,
                        onValueChange = {
                            precio = it
                            errorPrecio = it.toDoubleOrNull() == null || it.toDoubleOrNull()!! < 0
                        },
                        label = { Text("Precio *") },
                        isError = errorPrecio,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = stock,
                        onValueChange = {
                            stock = it
                            errorStock = it.toIntOrNull() == null || it.toIntOrNull()!! < 0
                        },
                        label = { Text("Stock *") },
                        isError = errorStock,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = categoria,
                        onValueChange = { categoria = it },
                        label = { Text("Categoría") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = descripcionCorta,
                        onValueChange = { descripcionCorta = it },
                        label = { Text("Descripción corta") },
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = descripcionLarga,
                        onValueChange = { descripcionLarga = it },
                        label = { Text("Descripción larga") },
                        maxLines = 4,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    OutlinedTextField(
                        value = imagenUrl,
                        onValueChange = { imagenUrl = it },
                        label = { Text("URL de imagen") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val precioDouble = precio.toDoubleOrNull()
                    val stockInt = stock.toIntOrNull()

                    if (nombre.isNotBlank() &&
                        codigo.isNotBlank() &&
                        precioDouble != null && precioDouble >= 0 &&
                        stockInt != null && stockInt >= 0
                    ) {
                        onConfirm(
                            Producto(
                                codigo = codigo,
                                nombre = nombre,
                                precio = precio,
                                stock = stockInt.toString(),
                                categoria = categoria.ifBlank { "General" },
                                descripcionCorta = descripcionCorta.ifBlank { nombre },
                                descripcionLarga = descripcionLarga.ifBlank { nombre },
                                especificaciones = emptyList(),
                                puntuacion = "0.0",
                                comentarios = emptyList(),
                                imagenUrl = imagenUrl
                            )
                        )
                    }
                },
                enabled = nombre.isNotBlank() &&
                        codigo.isNotBlank() &&
                        !errorPrecio && precio.isNotBlank() &&
                        !errorStock && stock.isNotBlank()
            ) {
                Text("Agregar", color = NeonGreen)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
