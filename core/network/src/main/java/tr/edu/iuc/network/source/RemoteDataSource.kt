package tr.edu.iuc.network.source

import tr.edu.iuc.network.dto.ArticleDto
import tr.edu.iuc.network.dto.SentimentHistoryDto

interface RemoteDataSource {

    suspend fun getLatestArticles(limit: Int = 20, offset: Int = 0): List<ArticleDto>

    suspend fun getArticleById(articleId: String): ArticleDto

    suspend fun getArticlesByAsset(assetSymbol: String, limit: Int = 20, offset: Int = 0): List<ArticleDto>

    suspend fun getAssetSentiment(assetSymbol: String): Float

    suspend fun getAssetSentimentHistory(assetSymbol: String, period: String = "24h"): SentimentHistoryDto

    suspend fun searchArticles(query: String, limit: Int = 20, offset: Int = 0): List<ArticleDto>

    suspend fun getSupportedAssets(): List<String>
}