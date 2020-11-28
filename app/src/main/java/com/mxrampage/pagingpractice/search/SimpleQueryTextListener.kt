package com.mxrampage.pagingpractice.search

import android.widget.SearchView

interface SimpleQueryTextListener : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
