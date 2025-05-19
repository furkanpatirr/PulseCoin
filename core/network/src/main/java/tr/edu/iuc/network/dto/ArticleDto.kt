package tr.edu.iuc.network.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticleDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("published_at")
    val publishedAt: Date, //java util

    @SerializedName("source")
    val source: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("asset")
    val asset: String,

    @SerializedName("sentiment_score")
    val sentimentScore: Float,

    @SerializedName("sentiment_label")
    val sentimentLabel: String
)
