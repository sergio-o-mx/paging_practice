package com.mxrampage.pagingpractice.search.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mxrampage.pagingpractice.R
import com.mxrampage.pagingpractice.models.DefaultResponseModel

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: DefaultResponseModel, onItemClick: ((DefaultResponseModel) -> Unit)) {
        Glide.with(imageSearchResult.context)
            .load(item.urls.thumb)
            .centerCrop()
            .into(imageSearchResult)
        imageSearchResult.contentDescription = item.altDescription
        if (item.description == null) {
            textSearchDescription.text = item.altDescription
        } else {
            textSearchDescription.text = item.description
        }
        itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    private val imageSearchResult: ImageView = itemView.findViewById(R.id.imageSearchResult)
    private val textSearchDescription: TextView = itemView.findViewById(R.id.textSearchDescription)
}
