package com.example.toad.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toad.ui.theme.BackroundLight
import com.example.toad.ui.theme.GreenPrimary
import com.example.toad.ui.theme.GreenSecundary
import com.example.toad.ui.theme.White

// Datos Ejemplo

data class SolicitudItem(
    val id: String,
    val titulo: String,
    val categoria: String,
    val estado: ReporteEstado,
    val fecha: String,
    val descripcion: String
)

enum class ReporteEstado(val label: String, val color: Color) {
    PENDIENTE("Pendiente", Color(0xFFF57C00)),
    EN_PROCESO("En Proceso", Color(0xFF1976D2)),
    RESUELTO("Resuelto", Color(0xFF388E3C)),
    RECHAZADO("Rechazado", Color(0xFFD32F2F))
}

private val solicitudesMock = listOf(
    SolicitudItem("001", "Filtración de agua en techo", "Infraestructura", ReporteEstado.PENDIENTE,   "28/04/2026", "Hay una filtración de agua en el pasillo del segundo piso del edificio A."),
    SolicitudItem("002", "Aire acondicionado averiado", "Servicios",       ReporteEstado.EN_PROCESO,  "27/04/2026", "El A/C del aula 304 no enfría adecuadamente desde el lunes."),
    SolicitudItem("003", "Puerta rota en biblioteca",   "Seguridad",        ReporteEstado.RESUELTO,    "25/04/2026", "La puerta de emergencia de la biblioteca no cierra correctamente."),
    SolicitudItem("004", "Graffiti en fachada",          "Otros",            ReporteEstado.RECHAZADO,   "20/04/2026", "Hay graffiti en la pared exterior del edificio principal."),
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var selectedFilter by remember { mutableStateOf("Todos") }
    val filters = listOf("Todos", "Pendiente", "En Proceso", "Resuelto", "Rechazado")

    val filteredList = remember(selectedFilter) {
        if (selectedFilter == "Todos") solicitudesMock
        else solicitudesMock.filter { it.estado.label == selectedFilter }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Dashboard",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                        Text(
                            text = "Todas las solicitudes",
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
                        Icon(
                            imageVector = Icons.Default.Dashboard,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(22.dp)
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            item {
                MapPlaceholder()
            }

            item {
                Text(
                    text = "Filtrar Solicitudes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filters) { filter ->
                        FilterChip(
                            selected = selectedFilter == filter,
                            onClick = { selectedFilter = filter },
                            label = { Text(filter, fontSize = 13.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = GreenPrimary,
                                selectedLabelColor = White
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (filteredList.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.SearchOff,
                                contentDescription = null,
                                tint = Color.LightGray,
                                modifier = Modifier.size(56.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Sin resultados para \"$selectedFilter\"",
                                fontSize = 15.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                items(filteredList) { solicitud ->
                    SolicitudCard(solicitud = solicitud)
                }
            }
        }
    }
}

@Composable
private fun MapPlaceholder() {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(
            text = "Mapa de Reportes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFCFD8DC)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF90A4AE).copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = null,
                            tint = Color(0xFF455A64),
                            modifier = Modifier.size(34.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Mapa En Construcción",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF455A64)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Disponible en próximas actualizaciones",
                        fontSize = 13.sp,
                        color = Color(0xFF607D8B),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SolicitudCard(solicitud: SolicitudItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(solicitud.estado.color)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#${solicitud.id} · ${solicitud.categoria}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    EstadoBadge(estado = solicitud.estado)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = solicitud.titulo,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = solicitud.descripcion,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = solicitud.fecha,
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}

@Composable
private fun EstadoBadge(estado: ReporteEstado) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(estado.color.copy(alpha = 0.12f))
            .border(1.dp, estado.color.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = estado.label,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = estado.color
        )
    }
}
