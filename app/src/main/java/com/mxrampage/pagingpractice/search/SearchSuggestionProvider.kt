package com.mxrampage.pagingpractice.search

import android.content.SearchRecentSuggestionsProvider

class SearchSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.mxrampage.metovachallenge.search.SearchSuggestionProvider"
        const val MODE  = DATABASE_MODE_QUERIES
    }
}
