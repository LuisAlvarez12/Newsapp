package com.luisalvarez.openlibrary.network.repo

import com.luisalvarez.openlibrary.network.NoInternetException
import java.io.IOException

class NewsClient(
    private val newsAPI: NewsAPIService
) : NewsRepo {
    @Throws(IOException::class)
    override suspend fun search(searchString: String) = try {
        // query results from internet and cache them
        val results = newsAPI.searchAsync(query = searchString).await()
        results
    } catch (error: NoInternetException) {
        throw NoInternetException()
    } catch (error: IOException) {
        throw IOException()
    }
}