package com.link.newsfeedsapp.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.link.newsfeedsapp.core.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private var _articlesResponse: MutableLiveData<List<Article>> = MutableLiveData()
    private var _error: MutableLiveData<String> = MutableLiveData()
    val articlesResponse: MutableLiveData<List<Article>>
        get() = _articlesResponse
    val error: MutableLiveData<String>
        get() = _error

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val associatedPressArticles = async {
                repository.getAssociatedPressArticles()
            }
            val nextWebArticles = async {
                repository.getNextWebArticles()
            }

            if (associatedPressArticles.await().isSuccessful
                && nextWebArticles.await().isSuccessful
            ) {
                val list1: List<Article>? = associatedPressArticles.await().body()?.articles
                val list2: List<Article>? = nextWebArticles.await().body()?.articles

                _articlesResponse.postValue(merge(list1!!, list2!!))

            } else
                _error.postValue("some error happen")
        }
    }

    private fun merge(first: List<Article>, second: List<Article>): ArrayList<Article> {
        val list: ArrayList<Article> = ArrayList()
        list.addAll(first)
        list.addAll(second)
        return list
    }
}