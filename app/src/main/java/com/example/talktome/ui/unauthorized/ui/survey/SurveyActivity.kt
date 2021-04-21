package com.example.talktome.ui.unauthorized.ui.survey

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseActivity
import com.example.talktome.ui.authorized.MainActivity
import com.example.talktome.ui.unauthorized.ui.survey.viewBinders.GenderCheckListViewBinder
import com.example.talktome.ui.unauthorized.ui.survey.viewBinders.ListTitleViewBinder
import com.example.talktome.ui.unauthorized.ui.survey.viewBinders.ServiceCheckListViewBinder
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
        viewModel.onCreate(intent.extras)
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
        continueButton.isClickable = !isVisible
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
            fieldValidation()
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        initView.observe(this@SurveyActivity, Observer {
            val (genderList, serviceList, userGenderList) = it
            genderAdapter.apply {
                items = genderList
                notifyDataSetChanged()
            }

            serviceAdapter.apply {
                items = serviceList
                notifyDataSetChanged()
            }

            userGenderAdapter.apply {
                items = userGenderList
                notifyDataSetChanged()
            }
        })
        redirectToMain.observe(this@SurveyActivity, Observer {
            val intent = Intent(this@SurveyActivity, MainActivity::class.java)
            startActivity(intent)
            this@SurveyActivity.finish()
        })
    }

    private fun fieldValidation() {
        var isChecked = false

        when {
            nameEditText.text.toString().isNullOrEmpty() -> {
                isChecked = false
                nameEditText.error = "Поле не должно быть пустым"
            }
            secondNameView.text.toString().isNullOrEmpty() -> {
                isChecked = false
                secondNameView.error = "Поле не должно быть пустым"
            }
            ageView.text.toString().isNullOrEmpty() -> {
                isChecked = false
                ageView.error = "Поле не должно быть пустым"
            }
            viewModel.getUserGender() == null -> {
                isChecked = false
                Toast.makeText(this, "Выберите ваш пол", Toast.LENGTH_SHORT).show()
            }
            else -> {
                isChecked = true
            }
        }

        if (isChecked) {
            viewModel.onContinueButtonClick(
                nameEditText.text.toString(),
                secondNameView.text.toString(),
                ageView.text.toString()
            )
        }
    }
}