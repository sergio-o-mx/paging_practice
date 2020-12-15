package com.mxrampage.pagingpractice.network

import com.mxrampage.pagingpractice.search.SearchResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search/photos/")
    suspend fun searchPhotos(
        //@Query("client_id") client_id: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchResponseModel
}
