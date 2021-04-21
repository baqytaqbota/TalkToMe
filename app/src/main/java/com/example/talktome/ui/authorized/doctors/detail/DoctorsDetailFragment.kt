package com.example.talktome.ui.authorized.doctors.detail

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants.ARG_DOCTOR_DATA
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.ui.authorized.doctors.detail.viewBinders.DoctorEducationViewBinder
import com.example.talktome.ui.authorized.viewBinders.FeedbackItemView
import com.example.talktome.utils.decorator.VerticalItemDecorator
import com.example.talktome.utils.extensions.getTagsFromList
import kotlinx.android.synthetic.main.fragment_doctor_detail.*

class DoctorsDetailFragment : BaseFragment<DoctorsDetailViewModel>(
    DoctorsDetailViewModel::class,
    R.layout.fragment_doctor_detail
) {

    private val doctorData: DoctorListDTO
        get() = arguments?.getParcelable<DoctorListDTO>(ARG_DOCTOR_DATA) as DoctorListDTO

    private val educationAdapter = MultiTypeAdapter().apply {
        register(DoctorEducationViewBinder())
    }

    private val feedbackAdapter = MultiTypeAdapter().apply {
        register(FeedbackItemView())
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
    }

    override fun setupView() {
        super.setupView()
        setupAdapter()

        context?.let {
            Glide.with(it).load(doctorData.image).into(doctorImageView)
        }

        doctorName.text = doctorData.firstName + " " + doctorData.secondName
        doctorTags.text = getTagsFromList(doctorData.tags)
        doctorRatingBar.rating = doctorData.rating.toFloat()

        educationAdapter.apply {
            items = doctorData.education
            notifyDataSetChanged()
        }

        feedbackAdapter.apply {
            items = doctorData.feedback
            notifyDataSetChanged()
        }
    }

    private fun setupAdapter(){
        educationRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = educationAdapter
            if(itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(16, 16))
        }

        feedbackRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = feedbackAdapter
            if(itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(16, 16))
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        getSessionButton.setOnClickListener {
            //todo
        }
    }
}