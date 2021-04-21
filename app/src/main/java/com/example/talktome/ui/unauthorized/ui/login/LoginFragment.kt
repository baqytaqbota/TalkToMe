package com.example.talktome.ui.unauthorized.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.ui.authorized.MainActivity
import com.example.talktome.ui.unauthorized.ui.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailEditText
import kotlinx.android.synthetic.main.fragment_login.passwordEditText
import kotlinx.android.synthetic.main.fragment_login.progressLayout
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : BaseFragment<LoginViewModel>(LoginViewModel::class, R.layout.fragment_login){

    override fun setupView() {
        super.setupView()

    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
        loginButton.isClickable = !isVisible
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        registryTextView.setOnClickListener {
            findNavController().navigate(R.id.action_from_login_to_register)
        }
        loginButton.setOnClickListener {
            fieldValidation()
        }
    }

    override fun observeViewModel()=with(viewModel) {
        super.observeViewModel()

        redirectToMain.observe(viewLifecycleOwner, Observer {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        })
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
            else -> {
                isChecked = true
            }
        }

        if(isChecked) {
            viewModel.onLoginClick(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }
}