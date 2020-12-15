package com.mxrampage.pagingpractice.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.mxrampage.pagingpractice.R
import com.mxrampage.pagingpractice.databinding.FragmentSearchBinding
import com.mxrampage.pagingpractice.search.adapters.LoaderStateAdapter
import com.mxrampage.pagingpractice.search.adapters.SearchPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchPagingAdapter
    private lateinit var searchLoadStateAdapter: LoaderStateAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        searchBinding.lifecycleOwner = this
        searchBinding.searchViewModel = searchViewModel
        requireActivity().title = resources.getString(R.string.search_title)
        setupAdapters()
        setupSearchView()
        setupRecyclerView()
        return searchBinding.root
    }

    private fun setupAdapters() {
        searchAdapter = SearchPagingAdapter()
        searchLoadStateAdapter = LoaderStateAdapter { searchAdapter.retry() }
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
                    searchViewModel.getImagesFromWeb(it).observe(requireActivity(), {
                        lifecycleScope.launch {
                            searchAdapter.submitData(it)
                        }
                    })
                }
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        searchAdapter.onItemClick = { item ->
            searchBinding.root.findNavController()
                .navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.urls.full))
        }
        searchBinding.recyclerSearch.adapter =
            searchAdapter.withLoadStateFooter(searchLoadStateAdapter)
    }
}
