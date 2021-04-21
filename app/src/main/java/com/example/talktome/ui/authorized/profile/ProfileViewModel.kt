package com.example.talktome.ui.authorized.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.doctors.model.DoctorFeedBack
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.data.patient.model.PatientInfoDTO
import com.example.talktome.domain.authorizedUser.MakeClearUserDataUseCase
import com.example.talktome.domain.doctorsUseCase.GetDoctorProfileUseCase
import com.example.talktome.domain.patient.GetPatientProfileUseCase

class ProfileViewModel(
    private val makeClearUserDataUseCase: MakeClearUserDataUseCase,
    private val getPatientProfileUseCase: GetPatientProfileUseCase,
    private val getDoctorProfileUseCase: GetDoctorProfileUseCase
) : BaseViewModel(){

    private val _patient = MutableLiveData<PatientInfoDTO>()
    val patient: LiveData<PatientInfoDTO>
        get() = _patient

    private val _doctor = MutableLiveData<DoctorListDTO>()
    val doctor: LiveData<DoctorListDTO>
        get() = _doctor

    private var patientInfo: PatientInfoDTO? = null

    private var doctorFeedBack: List<DoctorFeedBack> = emptyList()

    fun getPatientProfile(){
        getPatientProfileUseCase.request(
            params = Unit,
            onResult = {
                patientInfo = it
               _patient.value = it
            },
            loading = _loading,
            error = _error
        )
    }

    fun getDoctorProfile(){
        getDoctorProfileUseCase.request(
            params = Unit,
            onResult = {
                doctorFeedBack = it?.feedback.orEmpty()
                _doctor.value = it
            },
            loading = _loading,
            error = _error
        )
    }

    fun getPatientInfo() = patientInfo

    fun getDoctorFeedBack() = doctorFeedBack

    fun onExitButtonClick(){
        makeClearUserDataUseCase.clearUserData()
    }
}