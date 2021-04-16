package com.example.talktome.ui.unauthorized.ui.chooseRole

import androidx.navigation.fragment.findNavController
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import kotlinx.android.synthetic.main.fragment_choose_role.*

class ChooseRoleFragment : BaseFragment<ChooseRoleViewModel>(ChooseRoleViewModel::class,R.layout.fragment_choose_role){


    override fun setupView(){

    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
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

    override fun observeViewModel() {
        super.observeViewModel()

    }

}