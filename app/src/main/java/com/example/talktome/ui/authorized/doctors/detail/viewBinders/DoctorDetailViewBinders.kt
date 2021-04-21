package com.example.talktome.ui.authorized.doctors.detail.viewBinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.example.talktome.R
import com.example.talktome.data.doctors.model.DoctorEducation
import kotlinx.android.synthetic.main.item_doctor_education.view.*

class DoctorEducationViewBinder: ItemViewBinder<DoctorEducation, DoctorEducationViewBinder.ViewHolder>(){

    override fun onBindViewHolder(
        holder: DoctorEducationViewBinder.ViewHolder,
        item: DoctorEducation
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_doctor_education, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: DoctorEducation) = with(itemView){
            universityName.text = item.university
            majorView.text = item.major
            periodView.text = item.period
            cityView.text = item.city
        }
    }

}