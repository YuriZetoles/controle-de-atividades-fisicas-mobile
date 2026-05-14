package dev.fslab.academia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.fslab.academia.ui.theme.LocalAcademiaColors
import dev.fslab.academia.ui.viewmodel.TreinadorClienteUi
import dev.fslab.academia.ui.viewmodel.TreinadorHomeUiState
import dev.fslab.academia.ui.viewmodel.TreinadorHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreinadorSelecaoAlunoBottomSheet(
    onDismiss: () -> Unit,
    onAlunoSelecionado: (String) -> Unit,
    viewModel: TreinadorHomeViewModel = viewModel()
) {
    val colors = LocalAcademiaColors.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (uiState is TreinadorHomeUiState.Idle) {
            viewModel.carregar()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = colors.surface,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Selecionar Aluno",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "Fechar", tint = colors.textPrimary)
                }
            }

            when (uiState) {
                is TreinadorHomeUiState.Loading, TreinadorHomeUiState.Idle -> {
                    Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = colors.primary)
                    }
                }
                is TreinadorHomeUiState.Error -> {
                    Box(modifier = Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = (uiState as TreinadorHomeUiState.Error).message,
                            color = colors.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                is TreinadorHomeUiState.Empty -> {
                    Box(modifier = Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Nenhum aluno encontrado.",
                            color = colors.textSecondary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                is TreinadorHomeUiState.Success -> {
                    val clientes = (uiState as TreinadorHomeUiState.Success).clientes
                    LazyColumn {
                        items(clientes) { cliente ->
                            ItemAlunoSelecionavel(
                                cliente = cliente,
                                onClick = { onAlunoSelecionado(cliente.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemAlunoSelecionavel(cliente: TreinadorClienteUi, onClick: () -> Unit) {
    val colors = LocalAcademiaColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (cliente.fotoUrl.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colors.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Person, contentDescription = null, tint = colors.primary)
            }
        } else {
            AsyncImage(
                model = cliente.fotoUrl,
                contentDescription = cliente.nome,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = cliente.nome,
            style = MaterialTheme.typography.bodyLarge,
            color = colors.textPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}
