package com.luisalvarez.openlibrary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luisalvarez.openlibrary.network.NoInternetException
import com.luisalvarez.openlibrary.network.model.searchResponse.NewsResult
import com.luisalvarez.openlibrary.network.repo.NewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class HomeViewModel
@Inject
constructor() : ViewModel() {

    @Inject
    lateinit var newsAPIService: NewsClient

    private val _category1Items = MutableLiveData<List<NewsResult>>()
    private val _category2Items = MutableLiveData<List<NewsResult>>()
    private val _showArticleDialog = MutableLiveData<NewsResult>()

    val category1Items: LiveData<List<NewsResult>> = _category1Items
    val category2Items: LiveData<List<NewsResult>> = _category2Items
    val showArticleDialog: LiveData<NewsResult> = _showArticleDialog


    fun search(categories: List<String> = DEFAULT_CATEGORIES) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val c1Articles = newsAPIService.search(categories.first()).response.results
                _category1Items.postValue(c1Articles)
            } catch (error: NoInternetException) {
            } catch (error: IOException) {
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val c2Articles = newsAPIService.search(categories.last()).response.results
                _category2Items.postValue(c2Articles)
            } catch (error: NoInternetException) {
            } catch (error: IOException) {
            }
        }
    }

    fun init() {
        GlobalScope.launch {
            search()
        }
    }

    fun onCardClicked(result: NewsResult) {
        _showArticleDialog.postValue(result)
    }

    companion object {
        val DEFAULT_CATEGORIES = listOf("Covid", "Police")
    }
}