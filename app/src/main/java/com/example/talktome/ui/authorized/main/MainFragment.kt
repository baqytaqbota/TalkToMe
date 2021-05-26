package com.example.talktome.ui.authorized.main

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.cometchat.pro.uikit.ui_resources.utils.Utils
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.common.baseUI.BaseFragment
import com.example.talktome.common.constants.ARGConstants
import com.example.talktome.ui.authorized.viewBinders.DoctorsViewBinder
import com.example.talktome.ui.authorized.viewBinders.SessionViewBinder
import com.example.talktome.utils.decorator.VerticalItemDecorator
import com.example.talktome.utils.extensions.startChatIntent
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_main_doctor.*
import kotlinx.android.synthetic.main.layout_main_patient.*

class MainFragment : BaseFragment<MainViewModel>(MainViewModel::class, R.layout.fragment_main) {

    private val doctorsAdapter = MultiTypeAdapter().apply {
        register(
            DoctorsViewBinder(
                onDetailClick = {
                    val bundle = bundleOf(
                        ARGConstants.ARG_DOCTOR_DATA to it
                    )
                    findNavController().navigate(R.id.action_from_main_to_doctor_detail, bundle)
                },
                getSessionClick = {
                    val bundle = bundleOf(
                        ARGConstants.ARG_DOCTOR_DATA to it
                    )
                    findNavController().navigate(R.id.action_from_main_to_get_session, bundle)
                })
        )
    }

    private val sessionAdapter = MultiTypeAdapter().apply {
        register(SessionViewBinder())
    }

    override fun setupPatientView() {
        super.setupPatientView()
        patientView.visibility = View.VISIBLE
        viewModel.onPatientViewCreated()
        viewModel.getSuggestionDoctors()
        setPatientView()
    }

    override fun setupDoctorView() {
        super.setupDoctorView()
        doctorView.visibility = View.VISIBLE
        viewModel.onDoctorViewCreated()
        viewModel.getDoctorSessions()
        setDoctorView()
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        chatPatient.setOnClickListener {
            viewModel.onChatClicked()
        }
        chatDoctor.setOnClickListener {
            viewModel.onChatClicked()
        }
        videoPatient.setOnClickListener {
            viewModel.onVideoCallClicked()
        }
        videoDoctor.setOnClickListener {
            viewModel.onVideoCallClicked()
        }
        addDiary.setOnClickListener {
            findNavController().navigate(R.id.action_from_main_to_add_diary)
        }
    }

    private fun setPatientView() {
        doctorSuggestionRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = doctorsAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(48, 32))
        }
    }

    private fun setDoctorView() {
        doctorSessionRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = sessionAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(VerticalItemDecorator(48, 32))
        }
    }

    override fun observeViewModel() = with(viewModel) {
        super.observeViewModel()
        doctors.observe(viewLifecycleOwner, Observer {
            emptyDoctorSuggestion.isVisible = it.isNullOrEmpty()
            doctorSuggestionRecycler.isVisible = !it.isNullOrEmpty()
            doctorsAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
        })
        sessions.observe(viewLifecycleOwner, Observer {
            emptyDoctorSessions.isVisible = it.isNullOrEmpty()
            doctorSessionRecycler.isVisible = !it.isNullOrEmpty()
            sessionAdapter.apply {
                items = it
                notifyDataSetChanged()
            }
        })
        sessionDoctor.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                emptyPatientInfo.isVisible = false
                patientInfoView.isVisible = true
                emptySessionDoctorInfo.isVisible = false
                sessionDoctorInfo.isVisible = true
                patientName.text = it.patient
                doctorDateTextView.text = it.date
                doctorTimeTextView.text = it.time
            }
        })
        sessionPatient.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                emptyDoctorInfo.isVisible = false
                doctorInfoView.isVisible = true
                emptySessionPatientInfo.isVisible = false
                sessionPatientInfo.isVisible = true
                doctorName.text = it.doctor
                patientDateTextView.text = it.date
                patientTimeTextView.text = it.time
            }
        })
        videoCall.observe(viewLifecycleOwner, Observer {
            context?.let { context ->
                Utils.initiatecall(
                    context,
                    it,
                    CometChatConstants.RECEIVER_TYPE_USER,
                    CometChatConstants.CALL_TYPE_VIDEO
                )
            }
        })
        chat.observe(viewLifecycleOwner, Observer {
            CometChat.getUser(
                it,
                object : CometChat.CallbackListener<User>() {
                    override fun onSuccess(user: User?) {
                        user?.let { it1 ->
                            activity?.startChatIntent(it1)
                        }
                    }
                    override fun onError(p0: CometChatException?) {}
                })
        })
    }

}