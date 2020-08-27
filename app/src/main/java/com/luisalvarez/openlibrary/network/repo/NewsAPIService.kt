package com.luisalvarez.openlibrary.network.repo

import com.luisalvarez.openlibrary.network.model.searchResponse.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("search")
    fun searchAsync(
        @Query("api-key") apiKey: String = API_KEY,
        @Query("type") type: String = API_REQUEST_TYPE,
        @Query("show-blocks") showBlocks: String = API_BLOCK_TYPE,
        @Query("show-fields") fields: String = API_SHOW_FIELDS,
        @Query("q") query: String
    ): Deferred<NewsResponse>
}

private const val API_KEY = "324ed141-8ecd-4f25-be0a-872bd02c6a8a"
private const val API_REQUEST_TYPE = "article"
private const val API_BLOCK_TYPE = "body"
private const val API_SHOW_FIELDS = "starRating,headline,thumbnail,short-url"



