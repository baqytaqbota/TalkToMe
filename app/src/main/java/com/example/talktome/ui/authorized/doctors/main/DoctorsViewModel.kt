package com.example.talktome.ui.authorized.doctors.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.domain.doctorsUseCase.GetAllDoctorsUseCase
import com.example.talktome.domain.doctorsUseCase.GetDoctorsByTagUseCase

class DoctorsViewModel(
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase,
    private val getDoctorsByTagUseCase: GetDoctorsByTagUseCase
) : BaseViewModel() {

    private val _doctors = MutableLiveData<List<Any>>()
    val doctors: LiveData<List<Any>>
        get() = _doctors

    fun onViewCreated() {
        getAllDoctorsUseCase.request(
            params = Unit,
            onResult = {
                _empty.value = it?.isNullOrEmpty()
                _doctors.value = it
            },
            loading = _loading,
            error = _error
        )
    }
}