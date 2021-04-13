package com.example.talktome.ui.unauthorized.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnclickListener()
    }

    private fun setupView(){

    }

    private fun setOnclickListener(){
        registryTextView.setOnClickListener {
            findNavController().navigate(R.id.action_from_login_to_choose_role)
        }
    }
}