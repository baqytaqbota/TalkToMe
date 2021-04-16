package com.example.talktome.ui.authorized.survey

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseActivity
import com.example.talktome.ui.authorized.survey.viewBinders.*
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.activity_survey.*


class SurveyActivity : BaseActivity<SurveyViewModel>(SurveyViewModel::class, R.layout.activity_survey) {

    private val userGenderAdapter = MultiTypeAdapter().apply {
        register(ListTitleViewBinder())
        register(GenderCheckListViewBinder {
            viewModel.setUserGender(it)
        })
    }

    private val genderAdapter = MultiTypeAdapter().apply {
        register(ListTitleViewBinder())
        register(GenderCheckListViewBinder {
            viewModel.setPreferDoctorGender(it)
        })
    }

    private val serviceAdapter = MultiTypeAdapter().apply {
        register(ListTitleViewBinder())
        register(ServiceCheckListViewBinder {
            viewModel.setServiceList(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun setupView() {
        super.setupView()
        surveyGenderRecyclerView.apply {
            adapter = genderAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator())
        }

        surveyServiceRecyclerView.apply {
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator())
        }

        surveyUserGenderRecyclerView.apply {
            adapter = userGenderAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator())
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        continueButton.setOnClickListener {
            viewModel.onContinueButtonClick()
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        initView.observe(this@SurveyActivity, Observer {
            val (genderList, serviceList) = it
            genderAdapter.apply {
                items = genderList
                notifyDataSetChanged()
            }

            serviceAdapter.apply {
                items = serviceList
                notifyDataSetChanged()
            }
        })
    }
}