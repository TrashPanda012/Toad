package com.example.toad.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toad.ui.theme.BackroundLight
import com.example.toad.ui.theme.GreenPrimary
import com.example.toad.ui.theme.GreenSecundary
import com.example.toad.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onNavigateToReport: () -> Unit,
    onNavigateToDashboard: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Universidad Americana",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                        Text(
                            text = "Sistema de Reportes TOAD",
                            fontSize = 12.sp,
                            color = White.copy(alpha = 0.8f)
                        )
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "T",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        colors = listOf(GreenPrimary, GreenSecundary)
                    )
                )
            )
        },
        containerColor = BackroundLight
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(GreenPrimary, GreenSecundary)
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 28.dp)
            ) {
                Column {
                    Text(
                        text = "¡Bienvenido de vuelta!",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Ayúdanos a mantener nuestra universidad en perfectas condiciones reportando cualquier problema.",
                        fontSize = 14.sp,
                        color = White.copy(alpha = 0.85f),
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = onNavigateToReport,
                        colors = ButtonDefaults.buttonColors(containerColor = White),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = GreenPrimary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Nuevo Reporte",
                            color = GreenPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    number = "1",
                    label = "Pendientes",
                    icon = Icons.Default.PendingActions,
                    color = Color(0xFFF57C00)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    number = "1",
                    label = "En Proceso",
                    icon = Icons.Default.Engineering,
                    color = Color(0xFF1976D2)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    number = "1",
                    label = "Resueltos",
                    icon = Icons.Default.CheckCircle,
                    color = GreenPrimary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Acciones Rápidas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Crear Reporte",
                    subtitle = "Reporta un problema",
                    icon = Icons.Default.ReportProblem,
                    gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF2E7D32)),
                    onClick = onNavigateToReport
                )
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Dashboard",
                    subtitle = "Ver solicitudes",
                    icon = Icons.Default.Dashboard,
                    gradientColors = listOf(Color(0xFF009688), Color(0xFF00695C)),
                    onClick = onNavigateToDashboard
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Categorías de Reporte",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CategoryRow(
                    icon = Icons.Default.Construction,
                    title = "Infraestructura",
                    count = "1 reporte",
                    color = Color(0xFFF57C00)
                )
                CategoryRow(
                    icon = Icons.Default.Security,
                    title = "Seguridad",
                    count = "1 reporte",
                    color = Color(0xFFD32F2F)
                )
                CategoryRow(
                    icon = Icons.Default.Build,
                    title = "Servicios",
                    count = "1 reporte",
                    color = Color(0xFF1976D2)
                )
                CategoryRow(
                    icon = Icons.Default.MoreHoriz,
                    title = "Otros",
                    count = "1 reporte",
                    color = Color(0xFF7B1FA2)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    number: String,
    label: String,
    icon: ImageVector,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = number,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 13.sp,
                maxLines = 2,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
private fun QuickActionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(gradientColors))
                .padding(16.dp)
        ) {
            Column {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = White
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
private fun CategoryRow(
    icon: ImageVector,
    title: String,
    count: String,
    color: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = count,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF607D8B).copy(alpha = 0.12f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "En desarrollo :D",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF607D8B)
                )
            }
        }
    }
}