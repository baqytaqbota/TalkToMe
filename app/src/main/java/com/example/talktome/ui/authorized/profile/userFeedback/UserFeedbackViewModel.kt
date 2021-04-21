package com.example.talktome.ui.authorized.profile.userFeedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.domain.doctorsUseCase.GetDoctorProfileUseCase

class UserFeedbackViewModel(
    private val getDoctorProfileUseCase: GetDoctorProfileUseCase
) : BaseViewModel(){

    private val _feedback = MutableLiveData<List<Any>>()
    val feedback: LiveData<List<Any>>
        get() = _feedback

    fun onViewCreated(){
        getDoctorProfileUseCase.request(
            params = Unit,
            onResult = {
                _empty.value = it?.feedback.isNullOrEmpty()
                _feedback.value = it?.feedback.orEmpty()
            },
            loading = _loading,
            error = _error
        )
    }
}