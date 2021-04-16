package com.example.talktome.ui.unauthorized.ui.login

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.ui.authorized.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<LoginViewModel>(LoginViewModel::class, R.layout.fragment_login){

    override fun setupView() {
        super.setupView()

    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        registryTextView.setOnClickListener {
            findNavController().navigate(R.id.action_from_login_to_register)
        }
        loginButton.setOnClickListener {
            val intent = Intent(activity, SurveyActivity::class.java)
            startActivity(intent)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()

    }
}