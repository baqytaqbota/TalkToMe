package com.example.talktome.ui.authorized.session

import android.app.AlertDialog
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
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

    private val multiAdapter = MultiTypeAdapter().apply {
        register(TimeViewBinder {
            time = it.time
            timeLong = it.timeLong
        })
    }

    private var selectedDate = ""
    private var time = ""
    private var timeLong = 0L

    private var selectedDateLong = 0L

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

    private fun initCurrentDay() {
        val date: Long = calendarView.getDate()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        selectedDate = "$year-$month-$day"
        selectedDateLong = date
    }

    private fun setCalendarView() {
        calendarView.minDate = Date().time
        calendarView.maxDate = Date().time + (1000 * 60 * 60 * 24 * 6)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = "$year-$month-$dayOfMonth"
            selectedDateLong = view.date
        }
    }

    private fun setupAdapter() {
        timeRecycler.apply {
            adapter = multiAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(GridItemDecorator(2, 16))
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        onSuccess.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            showDialog()
        })
    }

    private fun showDialog() {
        AlertDialog.Builder(context)
            .setMessage("Вы успешно записались психотерапевту!")
            .setPositiveButton("Отлично") { dialog, _ ->
                dialog.dismiss()
                findNavController().popBackStack()
            }.show()
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()

        getSessionButton.setOnClickListener {
            validateCreation()
        }
    }

    private fun validateCreation() {
        if (time.isEmpty()) {
            Toast.makeText(context, "Выберите время", Toast.LENGTH_SHORT).show()
        } else if (selectedDate.isEmpty()) {
            Toast.makeText(context, "Выберите дату", Toast.LENGTH_SHORT).show()
        } else {
            val dateTime = selectedDateLong + timeLong
            viewModel.createSession(doctorData._id, selectedDate, time, dateTime)
        }
    }

}