package dev.fslab.academia.model

import com.google.gson.annotations.SerializedName

data class SolicitarTreinadorRequest(
    @SerializedName("treinador_id") val treinadorId: String
)

data class SolicitacaoData(
    @SerializedName("id") val id: String,
    @SerializedName("aluno_id") val alunoId: String,
    @SerializedName("treinador_id") val treinadorId: String,
    @SerializedName("status") val status: String,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("treinador") val treinador: TreinadorData? = null,
    @SerializedName("aluno") val aluno: SolicitacaoAlunoData? = null
)

data class SolicitacaoAlunoData(
    @SerializedName("id") val id: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("url_foto") val urlFoto: String? = null
)

data class ResponderSolicitacaoRequest(
    @SerializedName("status") val status: String
)

data class SolicitacaoResponse(
    @SerializedName("error") val error: Boolean = false,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: SolicitacaoData? = null,
    @SerializedName("errors") val errors: List<Map<String, Any?>> = emptyList()
)

data class SolicitacaoListResponse(
    @SerializedName("error") val error: Boolean = false,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: List<SolicitacaoData>? = null,
    @SerializedName("errors") val errors: List<Map<String, Any?>> = emptyList()
)
