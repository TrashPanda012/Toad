package com.example.toad.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun App(modifier: Modifier){
    var logged by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        AnimatedVisibility(
            visible = !logged,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            LoginScreen(onLogin = { logged = true })
        }
        AnimatedVisibility(
            visible = logged,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            HomeScreen(onLogout = { logged = false })
        }
    }
}