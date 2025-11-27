package com.levelup.gamer.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Botones flotantes de navegación rápida
 * Aparecen con animación y permiten volver atrás o ir al inicio
 */
@Composable
fun FloatingNavigationButtons(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,
    showHome: Boolean = true,
    showBack: Boolean = true
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        visible = true
    }
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón Home
            AnimatedVisibility(
                visible = visible && showHome,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(300, delayMillis = 100)
                ) + fadeIn(animationSpec = tween(300, delayMillis = 100)),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(200)
                ) + fadeOut(animationSpec = tween(200))
            ) {
                FloatingActionButton(
                    onClick = onHomeClick,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Ir al inicio"
                    )
                }
            }
            
            // Botón Atrás
            AnimatedVisibility(
                visible = visible && showBack,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(300, delayMillis = 200)
                ) + fadeIn(animationSpec = tween(300, delayMillis = 200)),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(200)
                ) + fadeOut(animationSpec = tween(200))
            ) {
                FloatingActionButton(
                    onClick = onBackClick,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver atrás"
                    )
                }
            }
        }
    }
}
