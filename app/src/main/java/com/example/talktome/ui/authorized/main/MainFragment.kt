package com.example.talktome.ui.authorized.main

import android.view.View
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainViewModel>(MainViewModel::class, R.layout.fragment_main){


    override fun setupPatientView() {
        super.setupPatientView()
        patientView.visibility = View.VISIBLE

    }

    override fun setupDoctorView() {
        super.setupDoctorView()
        doctorView.visibility = View.VISIBLE
    }

}