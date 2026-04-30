package com.example.toad.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

@Composable
fun App(modifier: Modifier){
    var logged by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()){

        // LOGIN
        AnimatedVisibility(
            visible = !logged,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            LoginScreen(onLogin = { logged = true })
        }

        // HOME
        AnimatedVisibility(
            visible = logged,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            HomeScreen(
                onLogout = { logged = false },

                onCreateReport = {
                    println("Ir a crear reporte")
                },

                onViewDashboard = {
                    println("Ir al dashboard")
                }
            )
        }
    }
}