package tr.edu.iuc.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tr.edu.iuc.network.dto.ArticleDto
import tr.edu.iuc.network.dto.SentimentHistoryDto

interface NewsApiService {

    @GET("articles")
    suspend fun getLatestArticles(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): List<ArticleDto>

    @GET("articles/{id}")
    suspend fun getArticleById(
        @Path("id") articleId: String
    ): ArticleDto

    @GET("articles/asset/{assetSymbol}")
    suspend fun getArticlesByAsset(
        @Path("assetSymbol") assetSymbol: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): List<ArticleDto>

    @GET("sentiment/{assetSymbol}")
    suspend fun getAssetSentiment(
        @Path("assetSymbol") assetSymbol: String
    ): Float

    @GET("sentiment/{assetSymbol}/history")
    suspend fun getAssetSentimentHistory(
        @Path("assetSymbol") assetSymbol: String,
        @Query("period") period: String = "24h" // Options: "1h", "24h", "7d", "30d"
    ): SentimentHistoryDto

    @GET("search")
    suspend fun searchArticles(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): List<ArticleDto>

    @GET("assets")
    suspend fun getSupportedAssets(): List<String>
}