package com.example.toad.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toad.ui.theme.GreenPrimary
import com.example.toad.ui.theme.GreenSecundary
import com.example.toad.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onLogin: () -> Unit){
    val scope = rememberCoroutineScope()
    var cif by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var attemptedLogin by remember { mutableStateOf(false) }
    
    val isCifValid = cif.length == 8 && cif.all { it.isDigit() }
    val isPasswordValid = password.length >= 8

    val cifError = attemptedLogin && !isCifValid
    val passwordError = attemptedLogin && !isPasswordValid
    
    var isLoading by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(
        brush = Brush.verticalGradient(
            colors = listOf(
                GreenPrimary,
                GreenSecundary
            )
        )
    ), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            modifier = Modifier.size(120.dp)
                .clip(CircleShape)
                .background(White),
            contentAlignment = Alignment.Center
        ){
            Text("TOAD", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = GreenPrimary)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Universidad Americana", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = White)
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth().
            padding(horizontal = 24.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ){
            Column(modifier = Modifier.padding(24.dp)) {
                OutlinedTextField(
                    value = cif,
                    onValueChange = { if (it.length <= 8) cif = it },
                    label = { Text("CIF") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = cifError,
                    supportingText = {
                        if(cifError){
                            Text("Ingrese un CIF válido (8 dígitos)")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = passwordError,
                    supportingText = {
                        if (passwordError) {
                            Text("La contraseña debe tener al menos 8 caracteres")
                        }
                    },
                    visualTransformation =
                        if (showPassword)
                            androidx.compose.ui.text.input.VisualTransformation.None
                        else
                            androidx.compose.ui.text.input.PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,
                                contentDescription = if (showPassword)
                                    "Ocultar contraseña"
                                else
                                    "Mostrar contraseña"
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(26.dp))
                Button(
                    onClick = {
                        attemptedLogin = true
                        if (isCifValid && isPasswordValid) {
                            scope.launch {
                                isLoading = true
                                delay(2000)
                                isLoading = false
                                onLogin()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
                ){
                    if (isLoading){
                        CircularProgressIndicator(color = White, modifier = Modifier.size(24.dp))
                    }else{
                        Text("Iniciar Sesión", color = White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
