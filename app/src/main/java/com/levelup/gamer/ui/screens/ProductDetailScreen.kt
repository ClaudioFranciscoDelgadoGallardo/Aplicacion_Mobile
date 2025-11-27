package com.levelup.gamer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.levelup.gamer.model.Producto
import com.levelup.gamer.ui.components.FloatingNavigationButtons
import com.levelup.gamer.viewmodel.ProductDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    producto: Producto,
    viewModel: ProductDetailViewModel = viewModel(),
    onBack: () -> Unit,
    onAddToCart: () -> Unit,
    onHomeClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Box {
        Scaffold(
            topBar = {
            TopAppBar(
                title = { Text(producto.nombre) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color(0xFF39FF14),
                    navigationIconContentColor = Color(0xFF39FF14)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .align(Alignment.CenterHorizontally),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.SportsEsports,
                            contentDescription = producto.nombre,
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally),
                            tint = Color(0xFF39FF14)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = producto.nombre,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color(0xFF39FF14),
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = producto.precio,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF1E90FF),
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Categoría: ${producto.categoria}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Stock disponible: ${producto.stock}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF39FF14)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF39FF14),
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = producto.descripcionLarga,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Especificaciones",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF39FF14),
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    producto.especificaciones.forEach { spec ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Icon(
                                Icons.Filled.Check,
                                contentDescription = null,
                                tint = Color(0xFF39FF14),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = spec,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Cantidad",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF39FF14),
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { viewModel.decrementQuantity() },
                            enabled = uiState.quantity > 1,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color(0xFF39FF14)
                            )
                        ) {
                            Icon(Icons.Filled.Remove, "Disminuir")
                        }
                        
                        Text(
                            text = uiState.quantity.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        
                        IconButton(
                            onClick = { viewModel.incrementQuantity() },
                            enabled = uiState.quantity < producto.stock.toIntOrNull() ?: 99,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color(0xFF39FF14)
                            )
                        ) {
                            Icon(Icons.Filled.Add, "Aumentar")
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = {
                    viewModel.markAsAddedToCart()
                    onAddToCart()
                },
                enabled = !uiState.isAddedToCart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF39FF14),
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = if (uiState.isAddedToCart) 
                        Icons.Filled.CheckCircle 
                    else 
                        Icons.Filled.ShoppingCart,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (uiState.isAddedToCart) 
                        "Agregado al carrito" 
                    else 
                        "Agregar al carrito",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
        
        FloatingNavigationButtons(
            onBackClick = onBack,
            onHomeClick = onHomeClick
        )
    }
}
