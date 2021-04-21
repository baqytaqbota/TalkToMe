package com.example.talktome.ui.unauthorized.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.authorization.model.LoginRequestBody
import com.example.talktome.domain.authorizedUser.SetTokenUseCase
import com.example.talktome.domain.authorizedUser.SetUserIdUseCase
import com.example.talktome.domain.authorizedUser.SetUserRoleUseCase
import com.example.talktome.domain.authorization.MakeLoginUseCase

class LoginViewModel(
    private val makeLoginUseCase: MakeLoginUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val setUserIdUseCase: SetUserIdUseCase,
    private val setUserRoleUseCase: SetUserRoleUseCase
) : BaseViewModel(){

    private val _redirectToMain = MutableLiveData<Unit>()
    val redirectToMain: LiveData<Unit>
        get() = _redirectToMain

    fun onLoginClick(email: String, password: String){
        makeLoginUseCase.request(
            params = LoginRequestBody(email, password),
            onResult = {
                setTokenUseCase.setToken(it?.token.orEmpty())
                setUserIdUseCase.setUserId(it?.userId.orEmpty())
                setUserRoleUseCase.setUserRole(it?.role.orEmpty())
                _redirectToMain.value = Unit
            },
            loading = _loading,
            error = _error
        )
    }
}