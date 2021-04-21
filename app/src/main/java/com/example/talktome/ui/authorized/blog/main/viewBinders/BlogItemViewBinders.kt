package com.example.talktome.ui.authorized.blog.main.viewBinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewBinder
import com.example.talktome.R
import com.example.talktome.data.blog.model.BlogItemDTO
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogItemViewBinder(val onClick: ((BlogItemDTO)-> Unit)) : ItemViewBinder<BlogItemDTO, BlogItemViewBinder.ViewHolder>(){
    override fun onBindViewHolder(holder: BlogItemViewBinder.ViewHolder, item: BlogItemDTO) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) =  ViewHolder(
        inflater.inflate(R.layout.item_blog, parent, false)
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        lateinit var item: BlogItemDTO

        init {
            with(itemView){
                blogItem.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }

        fun bind(item: BlogItemDTO) = with(itemView){
            this@ViewHolder.item = item
            //Glide.with(context).load(item.image).into(blogImageView)
            blogTitleView.text = item.title
            blogAuthorView.text = item.author
        }
    }
}