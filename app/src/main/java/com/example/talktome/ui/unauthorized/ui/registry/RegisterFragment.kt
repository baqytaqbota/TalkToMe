package com.example.talktome.ui.unauthorized.ui.registry

import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants.ARG_EMAIL
import com.example.talktome.common.constants.ARGConstants.ARG_PASSWORD
import com.example.talktome.ui.unauthorized.ui.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment<RegisterViewModel>(RegisterViewModel::class, R.layout.fragment_register){

    override fun setupView() {
        super.setupView()
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        registerButton.setOnClickListener {
            fieldValidation()
        }
    }

    override fun observeViewModel()= with(viewModel) {
        super.observeViewModel()

    }

    private fun fieldValidation() {
        var isChecked = false

        when{
            emailEditText.text.toString().isNullOrEmpty() -> {
                isChecked = false
                emailEditText.error = "Поле не должно быть пустым"
            }
            passwordEditText.text.toString().isNullOrEmpty() -> {
                isChecked = false
                passwordEditText.error = "Поле не должно быть пустым"
            }
            confirmPassEditText.text.isNullOrEmpty()-> {
                isChecked = false
                confirmPassEditText.error = "Поле не должно быть пустым"
            }
            passwordEditText.text.toString() != confirmPassEditText.text.toString() -> {
                isChecked = false
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
            else -> {
                isChecked = true
            }
        }

        if(isChecked) {
            val intent = Intent(activity, SurveyActivity::class.java)
            intent.putExtra(ARG_EMAIL,emailEditText.text.toString())
            intent.putExtra(ARG_PASSWORD, passwordEditText.text.toString())
            startActivity(intent)
        }

    }
}