package com.mxrampage.pagingpractice.search

import com.mxrampage.pagingpractice.models.DefaultResponseModel

data class SearchResponseModel(
    val results: List<DefaultResponseModel>
)
