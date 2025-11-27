package com.levelup.gamer.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.levelup.gamer.model.Producto
import kotlinx.coroutines.delay

@Composable
fun AnimatedProductCard(
    producto: Producto,
    onClick: () -> Unit,
    onAddToCart: () -> Unit,
    index: Int = 0
) {
    var visible by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }
    var showAddedFeedback by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 50L)
        visible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "card_scale"
    )

    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 8.dp,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "card_elevation"
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(300)) +
                slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(400)
                ),
        exit = fadeOut() + slideOutVertically()
    ) {
        Box {
            Card(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .scale(scale),
                elevation = CardDefaults.cardElevation(defaultElevation = elevation),
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
                        val iconSize by animateDpAsState(
                            targetValue = if (isPressed) 42.dp else 48.dp,
                            label = "icon_size"
                        )
                        
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
                                modifier = Modifier.size(iconSize)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                producto.categoria,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(12.dp)
                    ) {
                        Text(
                            producto.nombre,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            producto.descripcionCorta,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                producto.precio,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            AnimatedAddToCartButton(
                                onClick = {
                                    onAddToCart()
                                    showAddedFeedback = true
                                },
                                showFeedback = showAddedFeedback,
                                onFeedbackShown = { showAddedFeedback = false }
                            )
                        }
                    }
                }
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = showAddedFeedback,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(Color.Black.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = "Agregado",
                            tint = Color(0xFF39FF14),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "¡Agregado!",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(showAddedFeedback) {
        if (showAddedFeedback) {
            delay(1000)
            showAddedFeedback = false
        }
    }
}

@Composable
fun AnimatedAddToCartButton(
    onClick: () -> Unit,
    showFeedback: Boolean,
    onFeedbackShown: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = when {
            showFeedback -> 1.2f
            isPressed -> 0.9f
            else -> 1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "button_scale"
    )

    val rotation by animateFloatAsState(
        targetValue = if (showFeedback) 360f else 0f,
        animationSpec = tween(500),
        label = "button_rotation"
    )

    IconButton(
        onClick = {
            onClick()
            isPressed = true
        },
        modifier = Modifier
            .scale(scale)
            .size(40.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = if (showFeedback)
                Color(0xFF39FF14)
            else
                MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Icon(
            imageVector = if (showFeedback) Icons.Default.Check else Icons.Default.Add,
            contentDescription = "Agregar",
            tint = if (showFeedback)
                Color.Black
            else
                MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(100)
            isPressed = false
        }
    }
}

@Composable
fun PulsingLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.scale(scale),
            color = color.copy(alpha = alpha)
        )
    }
}

@Composable
fun AnimatedCartBadge(
    count: Int,
    modifier: Modifier = Modifier
) {
    var previousCount by remember { mutableStateOf(count) }
    var shouldAnimate by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (shouldAnimate) 1.3f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "badge_scale"
    )

    LaunchedEffect(count) {
        if (count > previousCount) {
            shouldAnimate = true
            delay(300)
            shouldAnimate = false
        }
        previousCount = count
    }

    AnimatedVisibility(
        visible = count > 0,
        enter = scaleIn() + fadeIn(),
        exit = scaleOut() + fadeOut()
    ) {
        Badge(
            modifier = modifier.scale(scale),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Text(
                if (count > 9) "9+" else count.toString(),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun ShimmerProductCard() {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")

    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                            .copy(alpha = shimmerAlpha)
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant
                                .copy(alpha = shimmerAlpha),
                            shape = MaterialTheme.shapes.small
                        )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant
                                .copy(alpha = shimmerAlpha),
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
    }
}

