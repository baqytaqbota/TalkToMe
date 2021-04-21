package com.example.talktome.ui.authorized.session.viewBinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.example.talktome.R
import com.example.talktome.ui.authorized.session.data.UISessionTime
import kotlinx.android.synthetic.main.item_checkable_grid_box.view.*

class TimeViewBinder(val listener: ((UISessionTime)-> Unit)): ItemViewBinder<UISessionTime, TimeViewBinder.ViewHolder>(){

    var previousCheckedPosition = -1
    var mCheckedPosition = -1

    override fun onBindViewHolder(holder: TimeViewBinder.ViewHolder, item: UISessionTime) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_checkable_grid_box, parent, false))

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: UISessionTime) = with(itemView){
            valueTextView.text = item.time

            val isChecked = layoutPosition == mCheckedPosition

            if (isChecked) {
                previousCheckedPosition = layoutPosition
                setCheckedView(true)
            } else {
                setCheckedView(false)
            }

            valueTextView.setOnClickListener {
                mCheckedPosition = if (isChecked) -1 else layoutPosition
                adapter.notifyItemChanged(previousCheckedPosition)
                adapter.notifyItemChanged(layoutPosition)
                listener.invoke(item)
            }
        }

        private fun setCheckedView(checked: Boolean) = with(itemView) {
            valueTextView.isChecked = checked
            when (checked) {
                true -> valueTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.textColorWithBackground
                    )
                )
                else -> valueTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.secondaryTextColor
                    )
                )
            }
        }
    }
}