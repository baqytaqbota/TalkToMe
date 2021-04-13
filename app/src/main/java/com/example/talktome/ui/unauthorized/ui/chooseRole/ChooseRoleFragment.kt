package com.example.talktome.ui.unauthorized.ui.chooseRole

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import kotlinx.android.synthetic.main.fragment_choose_role.*

class ChooseRoleFragment : Fragment(R.layout.fragment_choose_role){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnclickListener()
    }

    private fun setupView(){

    }

    private fun setOnclickListener(){
        patientCardView.setOnClickListener {
            findNavController().navigate(R.id.action_from_choose_role_to_register)
        }
        doctorCardView.setOnClickListener {
            findNavController().navigate(R.id.action_from_choose_role_to_register)
        }
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_from_choose_role_to_login)
        }
    }

}