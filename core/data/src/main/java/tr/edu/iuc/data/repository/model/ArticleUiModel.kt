package tr.edu.iuc.data.repository.model


import tr.edu.iuc.data.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ArticleUIModel(
    val id: String,
    val title: String,
    val content: String,
    val summary: String,
    val publishedAt: Date,
    val source: String,
    val url: String,
    val asset: String,
    val sentimentScore: Float,
    val sentimentLabel: String,
    val isFavorite: Boolean = false
) {
    /**
     * Returns sentiment color based on the score value
     */
    val sentimentColorRes: Int
        get() = when {
            sentimentScore >= 0.7f -> R.color.sentiment_positive
            sentimentScore <= 0.3f -> R.color.sentiment_negative
            else -> R.color.sentiment_neutral
        }

    /**
     * Returns formatted timestamp string
     */
    val formattedTime: String
        get() {
            val now = java.util.Date()
            val diff = now.time - publishedAt.time

            return when {
                diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)}m ago"
                diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)}h ago"
                else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(publishedAt)
            }
        }

    /**
     * Returns simple date format for display
     */
    val displayDate: String
        get() = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(publishedAt)

    companion object {
        /**
         * Creates an empty instance for previews or initial states
         */
        fun empty() = ArticleUIModel(
            id = "",
            title = "",
            content = "",
            summary = "",
            publishedAt = Date(),
            source = "",
            url = "",
            asset = "",
            sentimentScore = 0.5f,
            sentimentLabel = "Neutral",
            isFavorite = false
        )
    }
}