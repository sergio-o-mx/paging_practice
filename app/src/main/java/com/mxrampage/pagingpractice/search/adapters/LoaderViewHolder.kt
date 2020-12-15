package com.mxrampage.pagingpractice.search.adapters

import android.view.View
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.mxrampage.pagingpractice.R

class LoaderViewHolder(view: View, val retry: () -> Unit) : RecyclerView.ViewHolder(view) {

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Loading) {
            motionLayout.transitionToEnd()
        } else {
            motionLayout.transitionToStart()
        }
        retryButton.setOnClickListener {
            retry()
        }
    }

    private val motionLayout: MotionLayout = view.findViewById(R.id.layoutMotionLoader)
    private val retryButton: Button = view.findViewById(R.id.btnRetry)
}
