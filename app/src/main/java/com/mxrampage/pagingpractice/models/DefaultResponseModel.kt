package com.mxrampage.pagingpractice.models

import com.squareup.moshi.Json

data class DefaultResponseModel(
    val id: String,
    val description: String?,
    @Json(name = "alt_description") val altDescription: String?,
    val urls: URLs
)

data class URLs(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)
