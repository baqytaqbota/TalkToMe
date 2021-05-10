package com.example.talktome.ui.unauthorized.ui.survey

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.common.constants.ARGConstants.ARG_EMAIL
import com.example.talktome.common.constants.ARGConstants.ARG_PASSWORD
import com.example.talktome.common.constants.ChatConfig.CHAT_API_KEY
import com.example.talktome.data.authorization.model.RegisterRequestBody
import com.example.talktome.domain.authorizedUser.SetTokenUseCase
import com.example.talktome.domain.authorizedUser.SetUserIdUseCase
import com.example.talktome.domain.authorizedUser.SetUserRoleUseCase
import com.example.talktome.domain.authorization.MakeRegisterUseCase
import com.example.talktome.ui.unauthorized.ui.survey.data.UIGender
import com.example.talktome.ui.unauthorized.ui.survey.data.UIServiceType
import com.example.talktome.ui.unauthorized.ui.survey.data.UISurveyData
import com.example.talktome.ui.unauthorized.ui.survey.data.UISurveyDataDelegate

class SurveyViewModel(
    private val makeRegisterUseCase: MakeRegisterUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val setUserIdUseCase: SetUserIdUseCase,
    private val setUserRoleUseCase: SetUserRoleUseCase
) : BaseViewModel(), UISurveyData by UISurveyDataDelegate(){

    private val _initView = MutableLiveData<Triple<List<Any>, List<Any>, List<Any>>>()
    val initView: LiveData<Triple<List<Any>, List<Any>, List<Any>>>
        get() = _initView

    private val _redirectToMain = MutableLiveData<Unit>()
    val redirectToMain: LiveData<Unit>
        get() = _redirectToMain

    private var userGender: UIGender? = null
    private var preferDoctorGender: UIGender? = null
    private var listService = arrayListOf<String>()
    private var email: String? = null
    private var password: String? = null


    fun onCreate(bundle: Bundle?){
        if(bundle!=null){
            if(bundle.containsKey(ARG_EMAIL) && bundle.containsKey(ARG_PASSWORD)){
                email = bundle.getString(ARG_EMAIL)
                password = bundle.getString(ARG_PASSWORD)
            }
        }
        _initView.value = Triple(getGenderSurveyData(), getServiceSurveyData(), userGenderData())
    }

    fun getUserGender() = userGender

    fun setUserGender(gender: UIGender){
        userGender = gender
    }

    fun setPreferDoctorGender(gender: UIGender){
        preferDoctorGender = gender
    }

    fun setServiceList(list: List<UIServiceType>){
        listService.clear()
        list.forEach {
            listService.add(it.serviceType)
        }
    }

    fun onContinueButtonClick(name: String, secondName: String, age: String){
        val request = RegisterRequestBody(
            email = email.orEmpty(),
            password = password.orEmpty(),
            firstName = name,
            secondName = secondName,
            image = "",
            age = age.toInt(),
            sex = userGender?.value.orEmpty(),
            tags = listService
        )

        makeRegisterUseCase.request(params = request, onResult = {
            setTokenUseCase.setToken(it?.token.orEmpty())
            setUserIdUseCase.setUserId(it?.userId.orEmpty())
            setUserRoleUseCase.setUserRole(it?.role.orEmpty())

            val user = User()
            user.name = "$name $secondName"
            user.uid = it?.userId

            /**
             *  Создаем пользователя для чата
             */
            CometChat.createUser(user, CHAT_API_KEY, object : CometChat.CallbackListener<User>() {
                override fun onSuccess(user: User) {
                    Log.d("createUser", user.toString())
                    _redirectToMain.value = Unit
                }

                override fun onError(e: CometChatException) {
                    Log.e("createUser", e.message)
                }
            })

        }, loading = _loading, error =  _error)
    }
}