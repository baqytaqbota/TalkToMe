package com.example.talktome.ui.authorized.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.data.patient.model.PatientInfoDTO
import com.example.talktome.ui.unauthorized.ui.UnauthorizedActivity
import com.example.talktome.utils.extensions.getTagsFromList
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_doctor.*
import kotlinx.android.synthetic.main.layout_profile_patient.*

class ProfileFragment :
    BaseFragment<ProfileViewModel>(ProfileViewModel::class, R.layout.fragment_profile) {

    override fun setupDoctorView() {
        super.setupDoctorView()
        doctorLayout.isVisible = true
        setDoctorClickListeners()
        viewModel.getDoctorProfile()
    }

    override fun setupPatientView() {
        super.setupPatientView()
        patientLayout.isVisible = true
        setPatientClickListeners()
        viewModel.getPatientProfile()
    }

    override fun loaderState(isVisible: Boolean) {
        super.loaderState(isVisible)
        progressLayout.isVisible = isVisible
    }

    private fun setDoctorClickListeners() {
        doctorSessions.setOnClickListener {

        }
        doctorBlogs.setOnClickListener {
            findNavController().navigate(R.id.action_to_profile_blogs)
        }
        doctorFeedback.setOnClickListener {
            findNavController().navigate(R.id.action_to_profile_feedback)
        }
        doctorEditProfile.setOnClickListener {
            Toast.makeText(context, "Функционал в разработке",Toast.LENGTH_SHORT).show()
        }
        doctorExitAccount.setOnClickListener {
            showDialog()
        }
    }

    private fun setPatientClickListeners() {
        patientSessions.setOnClickListener {

        }
        patientEditProfile.setOnClickListener {
            Toast.makeText(context, "Функционал в разработке", Toast.LENGTH_SHORT).show()
        }
        patientExitAccount.setOnClickListener {
            showDialog()
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        patient.observe(viewLifecycleOwner, Observer {
            setPatientProfile(it)
        })
        doctor.observe(viewLifecycleOwner, Observer {
            setDoctorProfile(it)
        })
    }

    private fun setDoctorProfile(data: DoctorListDTO){
        if(!data.image.isNullOrEmpty())
            context?.let { Glide.with(it).load(data.image).into(doctorImageView) }

        doctorName.text = data.firstName + " " + data.secondName
        doctorTags.text = getTagsFromList(data.tags)
        doctorRatingBar.rating = data.rating.toFloat()
    }

    private fun setPatientProfile(data: PatientInfoDTO){
        if(!data.image.isNullOrEmpty())
            context?.let { Glide.with(it).load(data.image).into(patientImageView) }

        patientName.text = data.firstName + " " + data.secondName
        patientTags.text = getTagsFromList(data.tags)
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