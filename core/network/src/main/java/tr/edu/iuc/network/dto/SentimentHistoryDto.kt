package tr.edu.iuc.network.dto

import com.google.gson.annotations.SerializedName

data class SentimentHistoryDto(
    @SerializedName("asset")
    val asset: String,

    @SerializedName("period")
    val period: String,

    @SerializedName("data_points")
    val dataPoints: List<SentimentDataPointDto>
)

data class SentimentDataPointDto(
    @SerializedName("timestamp")
    val timestamp: Long,

    @SerializedName("sentiment_score")
    val sentimentScore: Float,

    @SerializedName("volatility")
    val volatility: Float? = null
)