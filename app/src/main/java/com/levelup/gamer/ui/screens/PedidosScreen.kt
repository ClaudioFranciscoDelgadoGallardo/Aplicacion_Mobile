package com.levelup.gamer.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.levelup.gamer.model.Pedido
import com.levelup.gamer.model.EstadoPedido
import com.levelup.gamer.viewmodel.PedidosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidosScreen(
    viewModel: PedidosViewModel,
    userId: Int,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.cargarPedidos(userId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Mis Pedidos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (uiState.pedidos.isEmpty()) {
                PedidosVaciosMessage()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.pedidos) { pedido ->
                        PedidoCard(
                            pedido = pedido,
                            onClick = {
                                viewModel.mostrarDetallePedido(pedido)
                            }
                        )
                    }
                }
            }
        }
    }

    // Dialog de detalle del pedido
    if (uiState.mostrarDetalle && uiState.pedidoActual != null) {
        PedidoDetalleDialog(
            pedido = uiState.pedidoActual!!,
            onDismiss = { viewModel.cerrarDetalle() }
        )
    }
}

@Composable
fun PedidosVaciosMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                Icons.Default.ShoppingBag,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
            Text(
                "No tienes pedidos aún",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "Tus pedidos aparecerán aquí después de realizar una compra",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoCard(
    pedido: Pedido,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )

    Card(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Pedido ${pedido.numeroPedido}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    pedido.getFechaFormateada(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                EstadoChip(pedido.estado)
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "$${String.format("%.2f", pedido.total)}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF39FF14)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = "Ver detalle",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            kotlinx.coroutines.delay(100)
            isPressed = false
        }
    }
}

@Composable
fun EstadoChip(estado: EstadoPedido) {
    val backgroundColor = when (estado) {
        EstadoPedido.PROCESANDO -> Color(0xFFFFA726)
        EstadoPedido.PREPARANDO -> Color(0xFF42A5F5)
        EstadoPedido.EN_CAMINO -> Color(0xFF66BB6A)
        EstadoPedido.ENTREGADO -> Color(0xFF39FF14)
    }

    Surface(
        color = backgroundColor.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                estado.getIcono(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                estado.descripcion,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = backgroundColor
            )
        }
    }
}

@Composable
fun PedidoDetalleDialog(
    pedido: Pedido,
    onDismiss: () -> Unit
) {
    var currentEstado by remember { mutableStateOf(pedido.estado) }

    // Simulación de progreso automático
    LaunchedEffect(pedido.id) {
        var estado = pedido.estado
        while (estado != EstadoPedido.ENTREGADO) {
            kotlinx.coroutines.delay(5000) // 5 segundos
            val siguiente = estado.siguiente()
            if (siguiente != null) {
                estado = siguiente
                currentEstado = estado
            } else {
                break
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(
                    "Pedido ${pedido.numeroPedido}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    pedido.getFechaFormateada(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Progreso visual
                EstadoProgreso(estadoActual = currentEstado)

                Divider()

                // Mensaje del estado actual
                EstadoMensaje(estado = currentEstado)

                // Tiempo estimado
                if (currentEstado != EstadoPedido.ENTREGADO) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Schedule,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                "Tiempo estimado: ${pedido.tiempoEstimadoEntrega}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }

                // Total
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total:",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "$${String.format("%.2f", pedido.total)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF39FF14)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

@Composable
fun EstadoProgreso(estadoActual: EstadoPedido) {
    val estados = EstadoPedido.values().sortedBy { it.orden }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        estados.forEach { estado ->
            val isActivo = estado.orden <= estadoActual.orden
            val isActual = estado == estadoActual

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Círculo indicador con animación
                val scale by animateFloatAsState(
                    targetValue = if (isActual) 1.2f else 1f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "scale"
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .scale(scale)
                        .background(
                            color = if (isActivo) Color(0xFF39FF14) else MaterialTheme.colorScheme.surfaceVariant,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (estado.orden < estadoActual.orden) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            estado.getIcono(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // Texto del estado
                Text(
                    estado.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (isActual) FontWeight.Bold else FontWeight.Normal,
                    color = if (isActivo) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Línea conectora (excepto en el último)
            if (estado != estados.last()) {
                Box(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .width(2.dp)
                        .height(16.dp)
                        .background(
                            if (estado.orden < estadoActual.orden)
                                Color(0xFF39FF14)
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                )
            }
        }
    }
}

@Composable
fun EstadoMensaje(estado: EstadoPedido) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Animación de pulso para el icono
            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "alpha"
            )

            Text(
                estado.getIcono(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.scale(if (estado != EstadoPedido.ENTREGADO) alpha else 1f)
            )

            Text(
                estado.getMensaje(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
