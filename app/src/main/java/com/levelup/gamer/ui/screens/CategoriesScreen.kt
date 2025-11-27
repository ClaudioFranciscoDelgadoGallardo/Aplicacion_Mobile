package com.levelup.gamer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levelup.gamer.viewmodel.CategoriesViewModel
import com.levelup.gamer.viewmodel.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = viewModel(),
    onBack: () -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    val categoryIcons = mapOf(
        "Consolas" to Icons.Filled.SportsEsports,
        "Juegos" to Icons.Filled.VideogameAsset,
        "Accesorios" to Icons.Filled.Headset,
        "PC Gaming" to Icons.Filled.Computer
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categorías") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.categories) { category ->
                val icon = categoryIcons[category.name] ?: Icons.Filled.Computer
                CategoryCard(
                    category = category,
                    icon = icon,
                    onClick = { 
                        viewModel.selectCategory(category.name)
                        onCategoryClick(category.name)
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = category.name,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF39FF14)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF39FF14),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = "${category.productCount} productos",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF1E90FF)
                )
            }
            
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF39FF14)
            )
        }
    }
}
