package tr.edu.iuc.database.source

import kotlinx.coroutines.flow.Flow
import tr.edu.iuc.database.ArticleDao
import tr.edu.iuc.database.ArticleEntity
import java.util.Date
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao
): LocalDataSource{
    override suspend fun getAllArticles(): Flow<List<ArticleEntity>> {
        return articleDao.getAllArticles()
    }

    override suspend fun getArticleById(articleId: String): ArticleEntity? {
        return articleDao.getArticleById(articleId)
    }

    override suspend fun getArticlesByAssets(assets: List<String>): Flow<List<ArticleEntity>> {
        return articleDao.getArticlesByAssets(assets)
    }

    override suspend fun insertArticles(articles: List<ArticleEntity>) {
        return articleDao.insertArticles(articles)
    }

    override suspend fun insertArticle(article: ArticleEntity) {
        return articleDao.insertArticle(article)
    }

    override suspend fun updateArticle(article: ArticleEntity) {
        return articleDao.updateArticle(article)
    }

    override suspend fun deleteArticle(articleId: String) {
        return articleDao.deleteArticle(articleId)
    }

    override suspend fun clearAllArticles() {
        return articleDao.clearAllArticles()
    }

    override suspend fun searchArticles(query: String): Flow<List<ArticleEntity>> {
        return articleDao.searchArticles(query)
    }

    override suspend fun getArticlesBySentiment(
        minScore: Float,
        maxScore: Float
    ): Flow<List<ArticleEntity>> {
        return articleDao.getArticlesBySentiment(minScore, maxScore)
    }

    override suspend fun toggleFavorite(articleId: String) {
        val article = articleDao.getArticleById(articleId)
        article?.let {
            val updatedArticle = it.copy(
                isFavorite = !it.isFavorite,
                updatedAt = Date()
            )
            articleDao.updateArticle(updatedArticle)
        }
    }
}