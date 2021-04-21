package com.example.talktome.ui.authorized.profile

import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.domain.authorizedUser.MakeClearUserDataUseCase

class ProfileViewModel(
    private val makeClearUserDataUseCase: MakeClearUserDataUseCase
) : BaseViewModel(){


    fun onExitButtonClick(){
        makeClearUserDataUseCase.clearUserData()
    }
}