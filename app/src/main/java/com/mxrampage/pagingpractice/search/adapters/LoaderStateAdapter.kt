package com.mxrampage.pagingpractice.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.mxrampage.pagingpractice.R

class LoaderStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_loader, parent, false)
        return LoaderViewHolder(view, retry)
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
