package com.example.talktome.data.authorizedUserData

import com.example.talktome.common.pref.Preference

class AuthUserRepository (private val pref: Preference){

    fun getToken() = pref.getToken()

    fun setToken(token: String) {
        pref.setToken(token)
    }

    fun getRole() = pref.getRole()

    fun setRole(role: String) {
        pref.setRole(role)
    }

    fun getUserId() = pref.getUserId()

    fun setUserId(userId: String) = pref.setUserId(userId)

    fun clearUserData(){
        pref.clearData()
    }

}