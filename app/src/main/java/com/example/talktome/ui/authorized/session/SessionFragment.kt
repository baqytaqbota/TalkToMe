package com.example.talktome.ui.authorized.session

import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.ui.authorized.session.viewBinders.TimeViewBinder
import com.example.talktome.utils.decorator.GridItemDecorator
import com.example.talktome.utils.extensions.getTagsFromList
import kotlinx.android.synthetic.main.fragment_session.*
import java.util.*


class SessionFragment :
    BaseFragment<SessionViewModel>(SessionViewModel::class, R.layout.fragment_session) {

    private val doctorData: DoctorListDTO
        get() = arguments?.getParcelable<DoctorListDTO>(ARGConstants.ARG_DOCTOR_DATA) as DoctorListDTO

    private val multiAdapter = MultiTypeAdapter().apply{
        register(TimeViewBinder {

        })
    }

    private var selectedDate = ""

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
        getSessionButton.isClickable = !isVisible
    }

    override fun setupView() {
        super.setupView()
        setCalendarView()
        setupAdapter()
        initCurrentDay()

        context?.let { Glide.with(it).load(doctorData.image).into(doctorImageView) }
        doctorName.text = doctorData.firstName + " " + doctorData.secondName
        doctorRatingBar.rating = doctorData.rating.toFloat()
        doctorTags.text = getTagsFromList(doctorData.tags)

        multiAdapter.apply {
            items = viewModel.getTimeList()
            notifyDataSetChanged()
        }
    }

    private fun initCurrentDay(){
        val date: Long = calendarView.getDate()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        selectedDate = "$year-$month-$day"
    }

    private fun setCalendarView() {
        calendarView.minDate = Date().time
        calendarView.maxDate = Date().time + (1000*60*60*24*6)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = "$year-$month-$dayOfMonth"
        }
    }

    private fun setupAdapter(){
        timeRecycler.apply {
            adapter = multiAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(GridItemDecorator(2, 16))
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()

        getSessionButton.setOnClickListener {
            Toast.makeText(context, selectedDate, Toast.LENGTH_SHORT).show()
        }
    }

}