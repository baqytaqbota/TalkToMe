package com.example.talktome.ui.authorized.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.session.model.SessionResultDTO
import com.example.talktome.domain.doctorsUseCase.GetDoctorsByTagUseCase
import com.example.talktome.domain.patient.GetPatientProfileUseCase
import com.example.talktome.domain.session.GetAllSessionsUseCase
import com.example.talktome.domain.session.GetNearSessionUseCase

class MainViewModel(
    private val getNearSessionUseCase: GetNearSessionUseCase,
    private val getAllSessionsUseCase: GetAllSessionsUseCase,
    private val getPatientProfileUseCase: GetPatientProfileUseCase,
    private val getDoctorsByTagUseCase: GetDoctorsByTagUseCase
) : BaseViewModel(){

    private val _doctors = MutableLiveData<List<Any>>()
    val doctors: LiveData<List<Any>>
        get() = _doctors

    private val _sessions = MutableLiveData<List<Any>>()
    val sessions: LiveData<List<Any>>
        get() = _sessions

    private val _sessionDoctor =  MutableLiveData<SessionResultDTO>()
    val sessionDoctor: LiveData<SessionResultDTO>
        get() = _sessionDoctor

    private val _sessionPatient =  MutableLiveData<SessionResultDTO>()
    val sessionPatient: LiveData<SessionResultDTO>
        get() = _sessionPatient

    fun onDoctorViewCreated(){
        getNearSessionUseCase.request(
            params = Unit,
            onResult = {
                _sessionDoctor.value = it
            },
            loading = _loading,
            error = _error
        )
    }

    fun onPatientViewCreated(){
        getNearSessionUseCase.request(
            params = Unit,
            onResult = {
                _sessionPatient.value = it
            },
            loading = _loading,
            error = _error
        )
    }

    fun getDoctorSessions(){
        getAllSessionsUseCase.request(
            params = Unit,
            onResult = {
                _sessions.value = it
            },
            loading = _loading,
            error = _error
        )
    }

    fun getSuggestionDoctors(){
        getPatientProfileUseCase.request(
            params = Unit,
            onResult = {
                getDoctorsByTagUseCase.request(
                    params = it?.tags.orEmpty(),
                    onResult = { doctors ->
                        _doctors.value = doctors
                    },
                    loading = _loading,
                    error = _error
                )
            },
            loading = _loading,
            error = _error
        )
    }
}