package com.example.talktome.ui.launcher.main

import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.domain.authorizedUser.GetTokenUseCase

class LauncherViewModel(
    private val getTokenUseCase: GetTokenUseCase
) : BaseViewModel(){


    fun isUserAuthorized() = !getTokenUseCase.getToken().isNullOrEmpty()

}