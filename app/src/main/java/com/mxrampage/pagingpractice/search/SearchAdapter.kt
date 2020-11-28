package com.mxrampage.pagingpractice.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mxrampage.pagingpractice.R
import com.mxrampage.pagingpractice.models.DefaultResponseModel

class SearchAdapter: ListAdapter<DefaultResponseModel, SearchViewHolder>(SearchDiffCallback()) {
    var onItemClick: ((DefaultResponseModel) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    internal class SearchDiffCallback : DiffUtil.ItemCallback<DefaultResponseModel>() {
        override fun areItemsTheSame(oldItem: DefaultResponseModel, newItem: DefaultResponseModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DefaultResponseModel, newItem: DefaultResponseModel): Boolean {
            return oldItem == newItem
        }
    }
}
