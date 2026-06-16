package dev.fslab.academia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingFlat
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.fslab.academia.model.ComparativoData
import dev.fslab.academia.model.EstatisticasData
import dev.fslab.academia.model.ExercicioFrequenteData
import dev.fslab.academia.model.GrupoMuscularData
import dev.fslab.academia.model.TipoExercicio
import dev.fslab.academia.ui.theme.AcademiaColors
import dev.fslab.academia.ui.viewmodel.ComparativoUiState

@Composable
fun StatsSection(stats: EstatisticasData, colors: AcademiaColors) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(
            "Estatísticas do período",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = colors.textSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(modifier = Modifier.weight(1f), value = "${stats.sessoesConcluidas}", label = "concluídas", colors = colors)
            StatCard(modifier = Modifier.weight(1f), value = "%.1fx".format(stats.treinosPorSemanaMedia), label = "/ semana", colors = colors)
            StatCard(
                modifier = Modifier.weight(1f),
                value = "${stats.mediaDuracaoMinutos} min",
                label = "duração média",
                colors = colors
            )
        }
        Spacer(Modifier.height(8.dp))
        StreakCard(sequenciaAtual = stats.sequenciaAtual, melhorSequencia = stats.melhorSequencia, colors = colors)
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(
                modifier = Modifier.weight(1f),
                value = "%.0f kg".format(stats.volumeTotalKg),
                label = "volume total",
                colors = colors
            )
        }
        if (stats.tempoTotalIsometriaSegundos > 0) {
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = formatarIsometriaStat(stats.tempoTotalIsometriaSegundos),
                    label = "isometria total",
                    colors = colors
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = formatarIsometriaStat(stats.mediaTempoIsometriaSegundos),
                    label = "isometria média",
                    colors = colors
                )
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, value: String, label: String, colors: AcademiaColors) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colors.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = colors.textPrimary)
            Text(label, style = MaterialTheme.typography.labelSmall, color = colors.textSecondary)
        }
    }
}

@Composable
fun StreakCard(sequenciaAtual: Int, melhorSequencia: Int, colors: AcademiaColors) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colors.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "🔥 $sequenciaAtual",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.featureOrange
                )
                Text(
                    if (sequenciaAtual == 1) "dia seguido" else "dias seguidos",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.textSecondary
                )
            }
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(colors.inputBorder)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "🏆 $melhorSequencia",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.textPrimary
                )
                Text("recorde pessoal", style = MaterialTheme.typography.labelSmall, color = colors.textSecondary)
            }
        }
    }
}

@Composable
fun GruposMusculareSection(grupos: List<GrupoMuscularData>, colors: AcademiaColors) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(
            "Grupos musculares",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = colors.textSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                grupos.take(5).forEach { grupo ->
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            grupo.grupoMuscular,
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.textSecondary,
                            modifier = Modifier.width(90.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        LinearProgressIndicator(
                            progress = { (grupo.percentual / 100f).toFloat().coerceIn(0f, 1f) },
                            modifier = Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(3.dp)),
                            color = colors.primary,
                            trackColor = colors.inputBorder
                        )
                        Text(
                            "%.0f%%".format(grupo.percentual),
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.textSecondary,
                            modifier = Modifier.width(34.dp)
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun ExerciciosFrequentesSection(
    frequentes: List<ExercicioFrequenteData>,
    colors: AcademiaColors,
    onTap: (ExercicioFrequenteData) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(
            "Exercícios mais treinados",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = colors.textSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(frequentes) { ex ->
                val eTempo = ex.tipo == TipoExercicio.TEMPO
                val iconeTint = colors.primary
                val subLabel = if (eTempo && ex.tempoTotalSegundos > 0)
                    formatarIsometriaStat(ex.tempoTotalSegundos)
                else
                    "${ex.totalSessoes} sessões"
                Card(
                    modifier = Modifier.clickable { onTap(ex) },
                    colors = CardDefaults.cardColors(containerColor = colors.surface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            if (eTempo) Icons.Default.Timer else Icons.AutoMirrored.Filled.TrendingUp,
                            contentDescription = null,
                            tint = iconeTint,
                            modifier = Modifier.height(16.dp).width(16.dp)
                        )
                        Column {
                            Text(ex.nome, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium, color = colors.textPrimary, maxLines = 1)
                            Text(subLabel, style = MaterialTheme.typography.labelSmall, color = iconeTint)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun SecaoEvolucaoPeriodo(
    state: ComparativoUiState,
    periodoLabel: String,
    colors: AcademiaColors
) {
    when (state) {
        is ComparativoUiState.Idle, is ComparativoUiState.Error -> Unit
        is ComparativoUiState.Loading -> {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    "Evolução do período",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colors.surface)
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = colors.primary,
                        modifier = Modifier.height(22.dp).width(22.dp),
                        strokeWidth = 2.dp
                    )
                }
            }
        }
        is ComparativoUiState.SemDados -> {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    "Evolução do período",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = colors.surface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Sem dados anteriores para comparar",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.textSecondary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        is ComparativoUiState.Success -> {
            ComparativoContent(
                data = state.data,
                periodoLabel = periodoLabel,
                colors = colors
            )
        }
    }
}

@Composable
private fun ComparativoContent(
    data: ComparativoData,
    periodoLabel: String,
    colors: AcademiaColors
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Evolução do período",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.textSecondary
            )
            Text(
                "vs período anterior",
                style = MaterialTheme.typography.labelSmall,
                color = colors.textSecondary.copy(alpha = 0.6f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ComparativoMetricaCard(
                modifier = Modifier.weight(1f),
                label = "sessões",
                valorAtual = "${data.periodoAtual.sessoesConcluidas}",
                valorAnterior = "${data.periodoAnterior.sessoesConcluidas}",
                pct = data.variacao.sessoesConcluídasPct,
                colors = colors
            )
            ComparativoMetricaCard(
                modifier = Modifier.weight(1f),
                label = "volume",
                valorAtual = formatarVolumeComparativo(data.periodoAtual.volumeTotalKg),
                valorAnterior = formatarVolumeComparativo(data.periodoAnterior.volumeTotalKg),
                pct = data.variacao.volumeTotalKgPct,
                colors = colors
            )
            ComparativoMetricaCard(
                modifier = Modifier.weight(1f),
                label = "duração",
                valorAtual = formatarDuracaoComparativo(data.periodoAtual.mediaDuracaoMinutos),
                valorAnterior = formatarDuracaoComparativo(data.periodoAnterior.mediaDuracaoMinutos),
                pct = data.variacao.mediaDuracaoMinutosPct,
                colors = colors
            )
        }
    }
}

@Composable
private fun ComparativoMetricaCard(
    modifier: Modifier = Modifier,
    label: String,
    valorAtual: String,
    valorAnterior: String,
    pct: Double?,
    colors: AcademiaColors
) {
    val (variacaoColor, variacaoIcon, variacaoTexto) = when {
        pct == null -> Triple(colors.textSecondary, Icons.AutoMirrored.Filled.TrendingFlat, "—")
        pct > 0 -> Triple(colors.primary, Icons.AutoMirrored.Filled.TrendingUp, "+${formatarPct(pct)}%")
        pct < 0 -> Triple(colors.error, Icons.AutoMirrored.Filled.TrendingDown, "${formatarPct(pct)}%")
        else -> Triple(colors.textSecondary, Icons.AutoMirrored.Filled.TrendingFlat, "0%")
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colors.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                label,
                style = MaterialTheme.typography.labelSmall,
                color = colors.textSecondary
            )
            Text(
                valorAtual,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    variacaoIcon,
                    contentDescription = null,
                    tint = variacaoColor,
                    modifier = Modifier.height(12.dp).width(12.dp)
                )
                Text(
                    variacaoTexto,
                    style = MaterialTheme.typography.labelSmall,
                    color = variacaoColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                valorAnterior,
                style = MaterialTheme.typography.labelSmall,
                color = colors.textSecondary.copy(alpha = 0.5f)
            )
        }
    }
}

fun formatarIsometriaStat(segundos: Int): String {
    val min = segundos / 60
    val seg = segundos % 60
    return if (min > 0) "${min}min ${seg}s" else "${seg}s"
}

private fun formatarVolumeComparativo(kg: Double): String = when {
    kg >= 1000 -> "${"%.1f".format(kg / 1000)}t"
    kg > 0 -> "${"%.0f".format(kg)}kg"
    else -> "0kg"
}

private fun formatarDuracaoComparativo(minutos: Int): String = when {
    minutos >= 60 -> "${minutos / 60}h${if (minutos % 60 > 0) "${minutos % 60}m" else ""}"
    minutos > 0 -> "${minutos}min"
    else -> "—"
}

private fun formatarPct(pct: Double): String =
    if (pct == pct.toLong().toDouble()) "${pct.toLong()}" else "${"%.1f".format(pct)}"
