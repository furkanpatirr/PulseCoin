package tr.edu.iuc.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    suspend fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE id = :articleId")
    suspend fun getArticleById(articleId: String): ArticleEntity?

    @Query("SELECT * FROM articles WHERE asset IN (:assets)")
    suspend fun getArticlesByAssets(assets: List<String>): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Update
    suspend fun updateArticle(article: ArticleEntity)

    @Query("DELETE FROM articles WHERE id = :articleId")
    suspend fun deleteArticle(articleId: String)

    @Query("DELETE FROM articles")
    suspend fun clearAllArticles()

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    suspend fun searchArticles(query: String): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE sentiment_score >= :minScore AND sentiment_score <= :maxScore")
    suspend fun getArticlesBySentiment(minScore: Float, maxScore: Float): Flow<List<ArticleEntity>>
}