package dev.fslab.academia.model

import com.google.gson.annotations.SerializedName

data class TreinadorData(
    @SerializedName("id") val id: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("url_foto") val urlFoto: String? = null,
    @SerializedName("cref") val cref: String,
    @SerializedName("turnos") val turnos: List<String> = emptyList(),
    @SerializedName("especializacao") val especializacao: String,
    @SerializedName("graduacao") val graduacao: String,
    @SerializedName("apresentacao") val apresentacao: String? = null,
)

data class TreinadorPaginationData(
    @SerializedName("dados") val dados: List<TreinadorData> = emptyList(),
    @SerializedName("total") val total: Int = 0,
    @SerializedName("page") val page: Int = 1,
    @SerializedName("limite") val limite: Int = 0,
    @SerializedName("totalPages") val totalPages: Int = 0
)

data class TreinadorListResponse(
    @SerializedName("error") val error: Boolean = false,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: TreinadorPaginationData? = null,
    @SerializedName("errors") val errors: List<Map<String, Any?>> = emptyList()
)

data class TreinadorSingleResponse(
    @SerializedName("error") val error: Boolean = false,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: TreinadorData? = null,
    @SerializedName("errors") val errors: List<Map<String, Any?>> = emptyList()
)
