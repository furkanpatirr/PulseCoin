package tr.edu.iuc.data.repository

import kotlinx.coroutines.flow.Flow
import tr.edu.iuc.common.ResponseResult
import tr.edu.iuc.data.repository.model.ArticleUIModel
import tr.edu.iuc.network.dto.SentimentHistoryDto

interface NewsRepository {
    suspend fun getNewsFeed(page: Int, pageSize: Int): Flow<ResponseResult<List<ArticleUIModel>>>

    suspend fun getNewsByAsset(assetSymbol: String): Flow<ResponseResult<List<ArticleUIModel>>>

    suspend fun getSentimentHistory(
        assetSymbol: String,
        timeRange: String
    ): Flow<ResponseResult<List<SentimentHistoryDto>>>

    suspend fun getFavoriteNews(): Flow<ResponseResult<List<ArticleUIModel>>>

    suspend fun toggleFavorite(articleId: String): Flow<ResponseResult<Boolean>>
}