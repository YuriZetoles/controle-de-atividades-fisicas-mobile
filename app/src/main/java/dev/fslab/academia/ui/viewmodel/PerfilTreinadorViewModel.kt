package dev.fslab.academia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fslab.academia.model.SolicitarTreinadorRequest
import dev.fslab.academia.model.TreinadorData
import dev.fslab.academia.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed interface PerfilTreinadorUiState {
    data object Idle : PerfilTreinadorUiState
    data object Loading : PerfilTreinadorUiState
    data class Success(val treinador: TreinadorData) : PerfilTreinadorUiState
    data class Error(val message: String) : PerfilTreinadorUiState
}

sealed interface SolicitacaoEnvioState {
    data object Idle : SolicitacaoEnvioState
    data object Enviando : SolicitacaoEnvioState
    data object Enviado : SolicitacaoEnvioState
    data class Erro(val message: String) : SolicitacaoEnvioState
}

class PerfilTreinadorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<PerfilTreinadorUiState>(PerfilTreinadorUiState.Idle)
    val uiState: StateFlow<PerfilTreinadorUiState> = _uiState.asStateFlow()

    private val _envioState = MutableStateFlow<SolicitacaoEnvioState>(SolicitacaoEnvioState.Idle)
    val envioState: StateFlow<SolicitacaoEnvioState> = _envioState.asStateFlow()

    fun carregar(treinadorId: String) {
        if (_uiState.value is PerfilTreinadorUiState.Loading) return
        viewModelScope.launch {
            _uiState.value = PerfilTreinadorUiState.Loading
            try {
                val response = RetrofitClient.treinadorApi.getTreinadorById(treinadorId)
                val data = response.data
                if (data != null) {
                    _uiState.value = PerfilTreinadorUiState.Success(data)
                } else {
                    _uiState.value = PerfilTreinadorUiState.Error("Treinador não encontrado")
                }
            } catch (e: Exception) {
                _uiState.value = PerfilTreinadorUiState.Error(e.message ?: "Erro ao carregar perfil")
            }
        }
    }

    fun solicitar(treinadorId: String) {
        if (_envioState.value is SolicitacaoEnvioState.Enviando ||
            _envioState.value is SolicitacaoEnvioState.Enviado) return

        viewModelScope.launch {
            _envioState.value = SolicitacaoEnvioState.Enviando
            try {
                RetrofitClient.solicitacaoApi.solicitarTreinador(
                    SolicitarTreinadorRequest(treinadorId = treinadorId)
                )
                _envioState.value = SolicitacaoEnvioState.Enviado
            } catch (e: HttpException) {
                val msg = when (e.code()) {
                    409 -> "Você já possui uma solicitação pendente ou treinador vinculado"
                    else -> "Erro ao enviar solicitação (${e.code()})"
                }
                _envioState.value = SolicitacaoEnvioState.Erro(msg)
            } catch (e: Exception) {
                _envioState.value = SolicitacaoEnvioState.Erro(e.message ?: "Erro de conexão")
            }
        }
    }

    fun resetEnvio() {
        _envioState.value = SolicitacaoEnvioState.Idle
    }
}
