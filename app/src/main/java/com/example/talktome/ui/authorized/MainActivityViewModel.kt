package com.example.talktome.ui.authorized

import android.util.Log
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.common.constants.ChatConfig.AUTH_KEY
import com.example.talktome.domain.authorizedUser.GetUserIdUseCase

class MainActivityViewModel(
    private val getUserIdUseCase: GetUserIdUseCase
) : BaseViewModel() {


    fun loginForChat() {
        if (CometChat.getLoggedInUser() == null) {
            CometChat.login(
                getUserIdUseCase.getUserId(),
                AUTH_KEY,
                object : CometChat.CallbackListener<User>() {
                    override fun onSuccess(p0: User?) {
                        Log.d("TAG", "Login Successful : " + p0?.toString())
                    }

                    override fun onError(p0: CometChatException?) {
                        Log.d("TAG", "Login failed with exception: " + p0?.message)
                    }
                })
        }
    }

}