package tr.edu.iuc.data.repository

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {

    override suspend fun getNewsFeed(
        page: Int,
        pageSize: Int
    ): Flow<ResponseResult<List<ArticleUIModel>>> = flow {
        emit(ResponseResult.Loading())
        try {
            // First try to get from local cache
            val localNews = localDataSource.getNewsFeed(page, pageSize)

            if (localNews.isNotEmpty()) {
                emit(ResponseResult.Success(localNews))
            }

            // Then fetch from remote regardless to update cache
            val remoteNews = remoteDataSource.getNewsFeed(page, pageSize)
            when (remoteNews) {
                is ResponseResult.Success -> {
                    // Save to local database
                    remoteNews.data?.let { articles ->
                        localDataSource.saveArticles(articles)
                        emit(ResponseResult.Success(articles))
                    }
                }

                is ResponseResult.Error -> {
                    if (localNews.isEmpty()) {
                        emit(ResponseResult.Error(remoteNews.message ?: "Unknown error"))
                    }
                }

                else -> {
                    // Do nothing if loading
                }
            }
        } catch (e: Exception) {
            emit(ResponseResult.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override suspend fun getNewsByAsset(assetSymbol: String): Flow<ResponseResult<List<ArticleUIModel>>> =
        flow {
            emit(ResponseResult.Loading())
            try {
                // Check local first
                val localNews = localDataSource.getNewsByAsset(assetSymbol)

                if (localNews.isNotEmpty()) {
                    emit(ResponseResult.Success(localNews))
                }

                // Then get from remote
                val remoteNews = remoteDataSource.getNewsByAsset(assetSymbol)
                when (remoteNews) {
                    is ResponseResult.Success -> {
                        remoteNews.data?.let { articles ->
                            localDataSource.saveArticles(articles)
                            emit(ResponseResult.Success(articles))
                        }
                    }

                    is ResponseResult.Error -> {
                        if (localNews.isEmpty()) {
                            emit(
                                ResponseResult.Error(
                                    remoteNews.message ?: "Failed to load asset news"
                                )
                            )
                        }
                    }

                    else -> {
                        // Do nothing if loading
                        //todo
                    }
                }
            } catch (e: Exception) {
                emit(ResponseResult.Error(e.message ?: "Unknown error fetching asset news"))
            }
        }

    override suspend fun getSentimentHistory(
        assetSymbol: String,
        timeRange: String
    ): Flow<ResponseResult<List<SentimentHistoryDto>>> = flow {
        emit(ResponseResult.Loading())
        try {
            val sentimentData = remoteDataSource.getSentimentHistory(assetSymbol, timeRange)
            emit(sentimentData)
        } catch (e: Exception) {
            emit(ResponseResult.Error(e.message ?: "Failed to fetch sentiment history"))
        }
    }

    override suspend fun getFavoriteNews(): Flow<ResponseResult<List<ArticleUIModel>>> = flow {
        emit(ResponseResult.Loading())
        try {
            val favoriteNews = localDataSource.getFavoriteNews()
            emit(ResponseResult.Success(favoriteNews))
        } catch (e: Exception) {
            emit(ResponseResult.Error(e.message ?: "Failed to load favorites"))
        }
    }

    override suspend fun toggleFavorite(articleId: String): Flow<ResponseResult<Boolean>> = flow {
        emit(ResponseResult.Loading())
        try {
            val result = localDataSource.toggleFavorite(articleId)
            emit(ResponseResult.Success(result))
        } catch (e: Exception) {
            emit(ResponseResult.Error(e.message ?: "Failed to update favorite status"))
        }
    }
}