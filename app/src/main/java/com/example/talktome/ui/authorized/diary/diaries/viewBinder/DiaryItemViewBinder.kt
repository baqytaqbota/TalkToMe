package com.example.talktome.ui.authorized.diary.diaries.viewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.example.talktome.R
import com.example.talktome.data.diary.model.DiaryDTO
import kotlinx.android.synthetic.main.item_diaries.view.*

class DiaryItemViewBinder(private val onClick: ((DiaryDTO) -> Unit)) : ItemViewBinder<DiaryDTO, DiaryItemViewBinder.ViewHolder>(){

    override fun onBindViewHolder(holder: DiaryItemViewBinder.ViewHolder, item: DiaryDTO) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_diaries, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: DiaryDTO) = with(itemView){
            diaryTitle.text = item.diaryTitle
            description.text = item.diaryDescription

            diaryView.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

}