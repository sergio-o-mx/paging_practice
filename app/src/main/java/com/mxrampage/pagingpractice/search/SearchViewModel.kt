package com.mxrampage.pagingpractice.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mxrampage.pagingpractice.models.DefaultResponseModel

class SearchViewModel @ViewModelInject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    fun getImagesFromWeb(query: String): LiveData<PagingData<DefaultResponseModel>> {
        return repository.searchPhotos(query)
    }
}
