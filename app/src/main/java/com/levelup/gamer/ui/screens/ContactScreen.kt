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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levelup.gamer.ui.components.FloatingNavigationButtons
import com.levelup.gamer.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    viewModel: ContactViewModel = viewModel(),
    onBack: () -> Unit,
    onHomeClick: () -> Unit = {}
) {
    val formState by viewModel.formState.collectAsState()
    
    Box {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Contáctenos") },
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
            Text(
                text = "¿Tienes preguntas? ¡Contáctanos!",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF39FF14),
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ContactInfoCard(
                icon = Icons.Filled.Email,
                title = "Email",
                content = "soporte@levelupgamer.com"
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            ContactInfoCard(
                icon = Icons.Filled.Phone,
                title = "Teléfono",
                content = "+56 9 1234 5678"
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            ContactInfoCard(
                icon = Icons.Filled.LocationOn,
                title = "Dirección",
                content = "Av. Gamer 123, Santiago, Chile"
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Envíanos un mensaje",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF39FF14),
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = formState.name,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = formState.nameError.isNotEmpty(),
                supportingText = {
                    if (formState.nameError.isNotEmpty()) {
                        Text(formState.nameError, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF39FF14),
                    focusedLabelColor = Color(0xFF39FF14),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            OutlinedTextField(
                value = formState.email,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = formState.emailError.isNotEmpty(),
                supportingText = {
                    if (formState.emailError.isNotEmpty()) {
                        Text(formState.emailError, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF39FF14),
                    focusedLabelColor = Color(0xFF39FF14),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            OutlinedTextField(
                value = formState.message,
                onValueChange = { viewModel.updateMessage(it) },
                label = { Text("Mensaje") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                maxLines = 5,
                isError = formState.messageError.isNotEmpty(),
                supportingText = {
                    if (formState.messageError.isNotEmpty()) {
                        Text(formState.messageError, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF39FF14),
                    focusedLabelColor = Color(0xFF39FF14),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = { viewModel.submitForm() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF39FF14),
                    contentColor = Color.Black
                ),
                enabled = viewModel.isFormValid() && !formState.isSubmitting
            ) {
                if (formState.isSubmitting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.Black
                    )
                } else {
                    Icon(Icons.Filled.Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Enviar mensaje",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
    
    if (formState.submitSuccess) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissSuccessDialog() },
            title = { Text("Mensaje enviado", color = Color(0xFF39FF14)) },
            text = { Text("Gracias por contactarnos. Te responderemos pronto.", color = Color.White) },
            confirmButton = {
                TextButton(onClick = { viewModel.dismissSuccessDialog() }) {
                    Text("OK", color = Color(0xFF39FF14))
                }
            },
            containerColor = Color(0xFF1A1A1A)
        )
    }
}

@Composable
fun ContactInfoCard(icon: ImageVector, title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = Color(0xFF39FF14)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1E90FF),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
        
        FloatingNavigationButtons(
            onBackClick = onBack,
            onHomeClick = onHomeClick
        )
    }
}
