package com.example.toad.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toad.ui.theme.GreenPrimary

// Pestañas

enum class MainTab(
    val label: String,
    val icon: ImageVector
) {
    HOME("Inicio", Icons.Default.Home),
    REPORT("Reporte", Icons.Default.ReportProblem),
    DASHBOARD("Dashboard", Icons.Default.Dashboard)
}

@Composable
fun App(modifier: Modifier) {
    var isLoggedIn by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = !isLoggedIn,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoginScreen(onLogin = { isLoggedIn = true })
        }

        AnimatedVisibility(
            visible = isLoggedIn,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MainScreen(onLogout = { isLoggedIn = false })
        }
    }
}

// Pantalla Main

@Composable
fun MainScreen(onLogout: () -> Unit) {
    var currentTab by remember { mutableStateOf(MainTab.HOME) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = currentTab == tab,
                        onClick = { currentTab = tab },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label
                            )
                        },
                        label = {
                            Text(
                                text = tab.label,
                                fontSize = 12.sp,
                                fontWeight = if (currentTab == tab) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = GreenPrimary,
                            selectedTextColor = GreenPrimary,
                            indicatorColor = GreenPrimary.copy(alpha = 0.12f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentTab) {
                MainTab.HOME -> HomeScreen(
                    onLogout = onLogout,
                    onNavigateToReport = { currentTab = MainTab.REPORT },
                    onNavigateToDashboard = { currentTab = MainTab.DASHBOARD }
                )
                MainTab.REPORT -> ReportCreationScreen(
                    onBack = { currentTab = MainTab.HOME }
                )
                MainTab.DASHBOARD -> DashboardScreen()
            }
        }
    }
}