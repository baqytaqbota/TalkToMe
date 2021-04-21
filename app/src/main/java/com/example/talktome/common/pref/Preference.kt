package com.example.talktome.common.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.talktome.common.constants.PrefConstants.PREF_GLOBAL_KEY
import com.example.talktome.common.constants.PrefConstants.PREF_ROLE
import com.example.talktome.common.constants.PrefConstants.PREF_TOKEN
import com.example.talktome.common.constants.PrefConstants.PREF_USER_ID

class Preference(context: Context){
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_GLOBAL_KEY, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun clearData() {
        editor.remove(PREF_TOKEN)
        editor.remove(PREF_ROLE)
        editor.remove(PREF_USER_ID)
        editor.apply()
    }

    fun getUserId() = sharedPreferences.getString(PREF_USER_ID, "").toString()

    fun setUserId(id: String){
        editor.putString(PREF_USER_ID, id)
        editor.apply()
    }

    fun getToken() = sharedPreferences.getString(PREF_TOKEN, "").toString()

    fun setToken(token : String) {
        editor.putString(PREF_TOKEN, token)
        editor.apply()
    }

    fun getRole() = sharedPreferences.getString(PREF_ROLE, "").toString()

    fun setRole(role: String){
        editor.putString(PREF_ROLE, role)
        editor.apply()
    }

}