package com.example.talktome.ui.authorized.viewBinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewBinder
import com.example.talktome.R
import com.example.talktome.data.doctors.model.DoctorFeedBack
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.data.session.model.SessionResultDTO
import com.example.talktome.utils.extensions.getTagsFromList
import kotlinx.android.synthetic.main.item_doctors.view.*
import kotlinx.android.synthetic.main.item_feedback.view.*
import kotlinx.android.synthetic.main.item_session.view.*

class DoctorsViewBinder(
    val onDetailClick: ((DoctorListDTO) -> Unit),
    val getSessionClick: ((DoctorListDTO) -> Unit)
) :
    ItemViewBinder<DoctorListDTO, DoctorsViewBinder.ViewHolder>() {
    override fun onBindViewHolder(holder: DoctorsViewBinder.ViewHolder, item: DoctorListDTO) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_doctors, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: DoctorListDTO

        init {
            with(itemView) {
                moreInfoBtn.setOnClickListener {
                    onDetailClick.invoke(item)
                }
                getSessionBtn.setOnClickListener {
                    getSessionClick.invoke(item)
                }
            }
        }

        fun bind(item: DoctorListDTO) = with(itemView) {
            this@ViewHolder.item = item
            Glide.with(context).load(item.image).into(doctorImageView)
            doctorName.text = item.firstName + " " + item.secondName
            doctorTags.text = getTagsFromList(item.tags)
            doctorRatingBar.rating = item.rating.toFloat()
        }
    }
}

class FeedbackItemView : ItemViewBinder<DoctorFeedBack, FeedbackItemView.ViewHolder>() {

    override fun onBindViewHolder(holder: FeedbackItemView.ViewHolder, item: DoctorFeedBack) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_feedback, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DoctorFeedBack) = with(itemView) {
            feedbackAuthor.text = item.patientId
            feedbackContent.text = item.comment
        }
    }
}

class SessionViewBinder : ItemViewBinder<SessionResultDTO, SessionViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: SessionViewBinder.ViewHolder, item: SessionResultDTO) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_session, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SessionResultDTO) = with(itemView) {
            patientName.text = item.patientId

            chatPatient.setOnClickListener {
                Toast.makeText(context, "Функционал в разработке", Toast.LENGTH_SHORT).show()
            }
        }
    }

}