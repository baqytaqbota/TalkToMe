package com.example.talktome.common.baseUI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.talktome.common.constants.Constants.ROLE_DOCTOR
import com.example.talktome.common.constants.Constants.ROLE_PATIENT
import com.example.talktome.domain.authorizedUser.GetUserRoleUseCase
import org.koin.java.KoinJavaComponent.inject

open class BaseViewModel : ViewModel(){

    private val roleUseCase by inject<GetUserRoleUseCase>(GetUserRoleUseCase::class.java)

    internal val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    internal val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    internal val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    private val _initPatientRole = MutableLiveData<Unit>()
    val initPatientRole : LiveData<Unit>
        get() = _initPatientRole

    private val _initDoctorRole = MutableLiveData<Unit>()
    val initDoctorRole : LiveData<Unit>
        get() = _initDoctorRole

    fun initRole(){
        when(roleUseCase.getUserRole()){
            ROLE_PATIENT -> _initPatientRole.value = Unit
            ROLE_DOCTOR -> _initDoctorRole.value = Unit
        }
    }

}