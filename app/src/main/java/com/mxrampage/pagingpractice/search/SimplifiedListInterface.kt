package com.mxrampage.pagingpractice.search

import com.mxrampage.pagingpractice.models.DefaultResponseModel

interface SimplifiedListInterface : List<DefaultResponseModel> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: DefaultResponseModel): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<DefaultResponseModel>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): DefaultResponseModel {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: DefaultResponseModel): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<DefaultResponseModel> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: DefaultResponseModel): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<DefaultResponseModel> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<DefaultResponseModel> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<DefaultResponseModel> {
        TODO("Not yet implemented")
    }
}