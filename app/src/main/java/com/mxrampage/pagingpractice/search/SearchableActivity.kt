package com.mxrampage.pagingpractice.search

import android.app.ListActivity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import com.mxrampage.pagingpractice.R

class SearchableActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doSearch(query)
            }
        }
    }

    private fun doSearch(query: String) {
        SearchRecentSuggestions(
            this,
            SearchSuggestionProvider.AUTHORITY,
            SearchSuggestionProvider.MODE
        ).saveRecentQuery(query, null)
    }
}
