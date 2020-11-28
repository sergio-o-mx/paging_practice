package com.mxrampage.pagingpractice.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private val _searchLiveData = MutableLiveData<SearchStateManager>()
    val searchLiveData: LiveData<SearchStateManager>
        get() = _searchLiveData

    fun getImagesFromWeb(query: String) {
        _searchLiveData.value = SearchStateManager.Loading
        viewModelScope.launch {
            try {
                val serviceResponse = repository.searchPhotos(query)
                /*val serviceResponse = repository.searchPhotos(query, page)
                _searchLiveData.postValue(
                    SearchStateManager.Message(serviceResponse.code().toString())
                )
                _searchLiveData.postValue(serviceResponse.body()?.let {
                    SearchStateManager.Response(it.results)
                })*/
            } catch (exception: Exception) {
                _searchLiveData.postValue(SearchStateManager.Error(exception))
            }
        }
    }
}
