package tr.edu.iuc.database.source

import kotlinx.coroutines.flow.Flow
import tr.edu.iuc.database.ArticleEntity

interface LocalDataSource {
    suspend fun getAllArticles(): Flow<List<ArticleEntity>>

    suspend fun getArticleById(articleId: String): ArticleEntity?

    suspend fun getArticlesByAssets(assets: List<String>): Flow<List<ArticleEntity>>

    suspend fun insertArticles(articles: List<ArticleEntity>)

    suspend fun insertArticle(article: ArticleEntity)

    suspend fun updateArticle(article: ArticleEntity)

    suspend fun deleteArticle(articleId: String)

    suspend fun clearAllArticles()

    suspend fun searchArticles(query: String): Flow<List<ArticleEntity>>

    suspend fun getArticlesBySentiment(minScore: Float, maxScore: Float): Flow<List<ArticleEntity>>

    suspend fun toggleFavorite(articleId: String)
}