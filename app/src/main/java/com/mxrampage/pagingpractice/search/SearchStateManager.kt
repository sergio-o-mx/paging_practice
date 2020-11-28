package com.mxrampage.pagingpractice.search

import com.mxrampage.pagingpractice.models.DefaultResponseModel

sealed class SearchStateManager {
    object Loading : SearchStateManager()
    class Message(val message: String) : SearchStateManager()
    class Response(val results: List<DefaultResponseModel>) : SearchStateManager()
    class Error(val exception: Exception) : SearchStateManager()
}
