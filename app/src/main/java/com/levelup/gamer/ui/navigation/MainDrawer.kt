package com.levelup.gamer.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class DrawerItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun MainDrawer(
    currentRoute: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerItems = listOf(
        DrawerItem("Inicio", Icons.Default.Home, "inicio"),
        DrawerItem("Categorías", Icons.Default.Category, "categorias"),
        DrawerItem("Noticias", Icons.Default.Article, "noticias"),
        DrawerItem("Contáctenos", Icons.Default.Email, "contacto"),
        DrawerItem("Iniciar Sesión", Icons.Default.Login, "login"),
        DrawerItem("Configuración", Icons.Default.Settings, "configuracion")
    )
    
    ModalDrawerSheet(
        modifier = modifier,
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            DrawerHeader()
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Divider()
            
            Spacer(modifier = Modifier.height(8.dp))
            
            drawerItems.forEach { item ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = item.title,
                            tint = if (currentRoute == item.route) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = if (currentRoute == item.route) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    },
                    selected = currentRoute == item.route,
                    onClick = { onItemClick(item.route) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Divider()
            
            DrawerFooter()
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Videogame,
                contentDescription = "Logo",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    "LEVEL-UP GAMER",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Tu tienda gamer de confianza",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun DrawerFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Versión 1.0.0",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "© 2025 Level-Up Gamer",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
