package com.example.chalenge1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chalenge1.models.NewsModel
import com.example.chalenge1.network.NetworkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _items = MutableLiveData<List<NewsModel>>()
    val items: LiveData<List<NewsModel>> get() = _items

    private val _loadingMore = MutableLiveData(false)
    val loadingMore: LiveData<Boolean> get() = _loadingMore

    private var noMoreData = false


    init {
        loadMore()
        CoroutineScope(Dispatchers.IO).launch {
            getNews()

        }
    }

    fun onScrollEndReached() {
        if (loadingMore.value == true || noMoreData) return
        loadMore()
    }

    fun onRefresh() {
        _items.postValue(emptyList())
        loadMore()
    }



   private fun getNews() {
        _loadingMore.postValue(true)
        val result = NetworkClient.newsService.getRequest()
        if (result.isSuccessful) {
            val items = result.body()
            _items.postValue(items!!)
        }
        _loadingMore.postValue(false)

    }

    private fun loadMore() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loadingMore.postValue(true)

            } catch (e: Exception) {

            } finally {
                _loadingMore.postValue(false)

            }
        }
    }
}