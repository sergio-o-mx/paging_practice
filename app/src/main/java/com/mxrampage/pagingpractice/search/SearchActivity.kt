package com.mxrampage.pagingpractice.search

import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mxrampage.pagingpractice.R
import com.mxrampage.pagingpractice.databinding.ActivitySearchBinding
import com.mxrampage.pagingpractice.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var searchBinding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        searchBinding.lifecycleOwner = this
        searchBinding.searchViewModel = searchViewModel
        setTitle(R.string.search_title)
        setupSearchView()
        setupRecyclerView()
        setupObserver()
    }

    private fun setupSearchView() {
        searchBinding.searchRequest.isActivated = true
        searchBinding.searchRequest.queryHint = resources.getString(R.string.search_view_hint)
        searchBinding.searchRequest.onActionViewExpanded()
        searchBinding.searchRequest.isIconified = false
        searchBinding.searchRequest.clearFocus()
        searchBinding.searchRequest.setOnQueryTextListener(object : SimpleQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    SearchRecentSuggestions(
                        this@SearchActivity,
                        SearchSuggestionProvider.AUTHORITY,
                        SearchSuggestionProvider.MODE
                    ).saveRecentQuery(it, null)
                    searchViewModel.getImagesFromWeb(it)
                }
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        searchAdapter.onItemClick = { item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("imageURL", item.urls.full)
            startActivity(intent)
        }
        searchBinding.recyclerSearch.adapter = searchAdapter
    }

    private fun setupObserver() {
        searchViewModel.searchLiveData.observe(this, {
            when (it) {
                is SearchStateManager.Loading -> searchBinding.progressBar.visibility = View.VISIBLE
                is SearchStateManager.Message -> {
                    searchBinding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is SearchStateManager.Response -> {
                    searchBinding.progressBar.visibility = View.GONE
                    searchAdapter.submitList(it.results)
                }
                is SearchStateManager.Error -> {
                    searchBinding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
