package com.levelup.gamer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class News(
    val title: String,
    val date: String,
    val summary: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(onBack: () -> Unit) {
    val newsList = listOf(
        News(
            "Nuevos lanzamientos de la temporada",
            "25 Oct 2025",
            "Descubre los juegos más esperados que llegarán este mes a todas las plataformas.",
            "Lanzamientos"
        ),
        News(
            "Ofertas especiales en consolas",
            "23 Oct 2025",
            "PlayStation 5 y Xbox Series X con descuentos increíbles por tiempo limitado.",
            "Ofertas"
        ),
        News(
            "Torneo Level-Up: Inscripciones abiertas",
            "20 Oct 2025",
            "Participa en nuestro torneo mensual y gana increíbles premios gaming.",
            "Eventos"
        ),
        News(
            "Llegaron las GPU RTX 5000",
            "18 Oct 2025",
            "La nueva generación de tarjetas gráficas NVIDIA ya está disponible en stock.",
            "Hardware"
        )
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Noticias Gaming") },
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
            items(newsList) { news ->
                NewsCard(news = news)
            }
        }
    }
}

@Composable
fun NewsCard(news: News) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AssistChip(
                    onClick = { },
                    label = { Text(news.category) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color(0xFF39FF14),
                        labelColor = Color.Black
                    )
                )
                
                Text(
                    text = news.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF39FF14),
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = news.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}
