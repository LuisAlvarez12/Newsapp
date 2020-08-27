package com.luisalvarez.openlibrary.network.repo

import com.luisalvarez.openlibrary.network.model.searchResponse.NewsResponse


interface NewsRepo {
    suspend fun search(searchString: String): NewsResponse
}