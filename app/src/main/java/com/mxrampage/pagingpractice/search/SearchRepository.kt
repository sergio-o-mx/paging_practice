package com.mxrampage.pagingpractice.search

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mxrampage.pagingpractice.BuildConfig
import com.mxrampage.pagingpractice.models.DefaultResponseModel
import com.mxrampage.pagingpractice.network.APIService
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiService: APIService
) {
    fun searchPhotos(query: String):
            LiveData<PagingData<DefaultResponseModel>> {
        return Pager(
            config = getDefaultPagingConfig(),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).liveData
    }

    private fun getDefaultPagingConfig(): PagingConfig {
        return PagingConfig(pageSize = BuildConfig.DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}
