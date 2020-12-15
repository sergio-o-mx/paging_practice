package com.mxrampage.pagingpractice.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mxrampage.pagingpractice.R
import com.mxrampage.pagingpractice.models.DefaultResponseModel

class SearchPagingAdapter : PagingDataAdapter<DefaultResponseModel, SearchViewHolder>(
    SearchPagingCallback()
) {
    var onItemClick: ((DefaultResponseModel) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClick) }
    }

    internal class SearchPagingCallback : DiffUtil.ItemCallback<DefaultResponseModel>() {
        override fun areItemsTheSame(oldItem: DefaultResponseModel, newItem: DefaultResponseModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DefaultResponseModel, newItem: DefaultResponseModel): Boolean {
            return oldItem == newItem
        }
    }
}
