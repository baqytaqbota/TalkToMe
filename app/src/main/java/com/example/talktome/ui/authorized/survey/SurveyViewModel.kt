package com.example.talktome.ui.authorized.survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.ui.authorized.survey.data.UIGender
import com.example.talktome.ui.authorized.survey.data.UIServiceType
import com.example.talktome.ui.authorized.survey.data.UISurveyData
import com.example.talktome.ui.authorized.survey.data.UISurveyDataDelegate

class SurveyViewModel : BaseViewModel(), UISurveyData by UISurveyDataDelegate(){

    private val _initView = MutableLiveData<Pair<List<Any>, List<Any>>>()
    val initView: LiveData<Pair<List<Any>, List<Any>>>
        get() = _initView

    private var userGender: UIGender? = null
    private var preferDoctorGender: UIGender? = null
    private var listService = arrayListOf<UIServiceType>()

    fun onCreate(){
        _initView.value = Pair(getGenderSurveyData(), getServiceSurveyData())
    }

    fun setUserGender(gender: UIGender){
        userGender = gender
    }

    fun setPreferDoctorGender(gender: UIGender){
        preferDoctorGender = gender
    }

    fun setServiceList(list: List<UIServiceType>){
        listService.addAll(list)
    }

    fun onContinueButtonClick(){
        //todo запрос в бэк, в случае успеха здесь наlо перенаправлять на main
    }
}