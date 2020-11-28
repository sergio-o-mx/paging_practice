package com.mxrampage.pagingpractice.search

import androidx.paging.PagingSource
import com.mxrampage.pagingpractice.models.DefaultResponseModel
import com.mxrampage.pagingpractice.network.APIService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchPagingSource (private val apiService: APIService, private val query: String) :
    PagingSource<Int, DefaultResponseModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DefaultResponseModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.searchPhotos(query, page)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
