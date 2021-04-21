package com.example.talktome.ui.authorized.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.core.view.isVisible
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.ui.unauthorized.ui.UnauthorizedActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_doctor.*
import kotlinx.android.synthetic.main.layout_profile_patient.*

class ProfileFragment :
    BaseFragment<ProfileViewModel>(ProfileViewModel::class, R.layout.fragment_profile) {

    override fun setupDoctorView() {
        super.setupDoctorView()
        doctorLayout.isVisible = true
        setDoctorClickListeners()
    }

    override fun setupPatientView() {
        super.setupPatientView()
        patientLayout.isVisible = true
        setPatientClickListeners()
    }

    private fun setDoctorClickListeners() {
        doctorSessions.setOnClickListener {

        }
        doctorBlogs.setOnClickListener {

        }
        doctorFeedback.setOnClickListener {

        }
        doctorEditProfile.setOnClickListener {

        }
        doctorExitAccount.setOnClickListener {
            showDialog()
        }
    }

    private fun setPatientClickListeners() {
        patientSessions.setOnClickListener {

        }
        patientFeedback.setOnClickListener {

        }
        patientEditProfile.setOnClickListener {

        }
        patientExitAccount.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(context)
            .setMessage("Вы точно хотите выйти?")
            .setPositiveButton("Да") { dialog, _ ->
                viewModel.onExitButtonClick()
                dialog.dismiss()
                val intent = Intent(activity, UnauthorizedActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}