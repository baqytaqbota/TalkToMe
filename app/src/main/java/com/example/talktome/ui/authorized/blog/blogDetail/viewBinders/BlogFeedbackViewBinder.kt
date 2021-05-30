package com.example.talktome.ui.authorized.blog.blogDetail.viewBinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.example.talktome.R
import com.example.talktome.data.blog.model.BlogFeedbackDTO
import kotlinx.android.synthetic.main.item_feedback.view.*

class BlogFeedbackViewBinder : ItemViewBinder<BlogFeedbackDTO, BlogFeedbackViewBinder.ViewHolder>(){

    override fun onBindViewHolder(
        holder: BlogFeedbackViewBinder.ViewHolder,
        item: BlogFeedbackDTO
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_feedback, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: BlogFeedbackDTO) = with(itemView){
            feedbackAuthor.text = item.patient
            feedbackContent.text = item.comment
        }
    }
}