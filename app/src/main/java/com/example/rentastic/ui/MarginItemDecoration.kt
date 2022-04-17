package com.example.rentastic.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize: Int, private val leftSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect:Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
            if (parent.getChildAdapterPosition(view) == 0){
                top = spaceSize
            }
            bottom = spaceSize
            left = leftSize
            right = leftSize
        }
    }
}