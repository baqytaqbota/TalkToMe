package com.example.talktome.utils.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(
    private val spanAmount: Int,
    private val innerMargin: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val marginRight = (innerMargin * 2 / 3)
        val marginLeft = (innerMargin / 3)
        val halfMargin = (innerMargin / 2)

        val itemPosition = parent.getChildAdapterPosition(view)
        when (spanAmount) {
            1 -> {
                // оставляем как есть
            }
            2 -> {
                when (itemPosition % spanAmount) {
                    0 -> outRect.right += halfMargin
                    1 -> outRect.left += halfMargin
                }
            }
            else -> {
                when (itemPosition % spanAmount) {
                    0 -> {
                        outRect.right += marginRight

                    }
                    spanAmount - 1 -> {
                        outRect.left += marginLeft
                    }
                    else -> {
                        outRect.right += marginRight
                        outRect.left += marginLeft
                    }
                }
            }
        }
    }
}
