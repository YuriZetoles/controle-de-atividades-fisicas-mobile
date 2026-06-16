package dev.fslab.academia.network

import dev.fslab.academia.model.ResponderSolicitacaoRequest
import dev.fslab.academia.model.SolicitacaoListResponse
import dev.fslab.academia.model.SolicitacaoResponse
import dev.fslab.academia.model.SolicitarTreinadorRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SolicitacaoApi {
    @POST("alunos/me/solicitar-treinador")
    suspend fun solicitarTreinador(@Body body: SolicitarTreinadorRequest): SolicitacaoResponse

    @GET("alunos/me/solicitacao")
    suspend fun buscarSolicitacaoDoAluno(): SolicitacaoResponse

    @GET("treinadores/me/solicitacoes")
    suspend fun listarSolicitacoesTreinador(
        @Query("status") status: String? = null
    ): SolicitacaoListResponse

    @PATCH("treinadores/me/solicitacoes/{id}")
    suspend fun responderSolicitacao(
        @Path("id") id: String,
        @Body body: ResponderSolicitacaoRequest
    ): SolicitacaoResponse
}
