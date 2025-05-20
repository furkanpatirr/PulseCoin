package tr.edu.iuc.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: String,

    val title: String,

    val content: String,

    val summary: String,

    @ColumnInfo(name = "published_at")
    val publishedAt: Date,

    val source: String,

    val url: String,

    val asset: String,

    @ColumnInfo(name = "sentiment_score")
    val sentimentScore: Float,

    @ColumnInfo(name = "sentiment_label")
    val sentimentLabel: String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Date = Date()
)
