package com.mxrampage.pagingpractice.search

import com.mxrampage.pagingpractice.models.DefaultResponseModel

data class SearchResponseModel(
    val results: List<DefaultResponseModel>
) : SimplifiedListInterface {
    override val size: Int
        get() = results.size

    override fun get(index: Int): DefaultResponseModel {
        return results[index]
    }

    override fun indexOf(element: DefaultResponseModel): Int {
        return results.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return results.isEmpty()
    }

    override fun lastIndexOf(element: DefaultResponseModel): Int {
        return results.lastIndexOf(element)
    }
}
