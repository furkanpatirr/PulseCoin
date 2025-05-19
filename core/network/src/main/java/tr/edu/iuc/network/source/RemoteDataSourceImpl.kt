package tr.edu.iuc.network.source

import tr.edu.iuc.network.api.NewsApiService
import tr.edu.iuc.network.dto.ArticleDto
import tr.edu.iuc.network.dto.SentimentHistoryDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: NewsApiService
) : RemoteDataSource {

    override suspend fun getLatestArticles(limit: Int, offset: Int): List<ArticleDto> {
        return apiService.getLatestArticles(limit, offset)
    }

    override suspend fun getArticleById(articleId: String): ArticleDto {
        return apiService.getArticleById(articleId)
    }

    override suspend fun getArticlesByAsset(assetSymbol: String, limit: Int, offset: Int): List<ArticleDto> {
        return apiService.getArticlesByAsset(assetSymbol, limit, offset)
    }

    override suspend fun getAssetSentiment(assetSymbol: String): Float {
        return apiService.getAssetSentiment(assetSymbol)
    }

    override suspend fun getAssetSentimentHistory(assetSymbol: String, period: String): SentimentHistoryDto {
        return apiService.getAssetSentimentHistory(assetSymbol, period)
    }

    override suspend fun searchArticles(query: String, limit: Int, offset: Int): List<ArticleDto> {
        return apiService.searchArticles(query, limit, offset)
    }

    override suspend fun getSupportedAssets(): List<String> {
        return apiService.getSupportedAssets()
    }
}