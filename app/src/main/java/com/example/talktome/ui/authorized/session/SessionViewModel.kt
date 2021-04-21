package com.example.talktome.ui.authorized.session

import com.example.talktome.common.baseUI.BaseViewModel

class SessionViewModel : BaseViewModel(){


    fun getTimeList() = mutableListOf<String>().apply{
        add("10:00 - 11:00")
        add("12:00 - 13:00")
        add("14:00 - 15:00")
        add("16:00 - 17:00")
    }

}