package com.example.talktome.ui.authorized.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.session.model.CreateSessionRequestBody
import com.example.talktome.domain.authorizedUser.GetUserIdUseCase
import com.example.talktome.domain.session.MakeCreateSessionUseCase
import com.example.talktome.ui.authorized.session.data.UISessionTime

class SessionViewModel(
    private val makeCreateSessionUseCase: MakeCreateSessionUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : BaseViewModel() {

    private val _onSuccess = MutableLiveData<Unit>()
    val onSuccess: LiveData<Unit>
        get() = _onSuccess

    fun createSession(doctorId: String, date: String, time: String, dateTime: Long) {
        val request = CreateSessionRequestBody(
            doctorId = doctorId,
            patientId = getUserIdUseCase.getUserId(),
            time = time,
            date = date,
            datetime = dateTime
        )

        makeCreateSessionUseCase.request(
            params = request,
            onResult = {
                _onSuccess.value = Unit
            },
            loading = _loading,
            error = _error
        )
    }


    fun getTimeList() = mutableListOf<UISessionTime>().apply {
        add(
            UISessionTime(
                "10:00 - 11:00",
                10.toLong()
            )
        )
        add(
            UISessionTime(
                "12:00 - 13:00",
                12.toLong()
            )
        )
        add(
            UISessionTime(
                "14:00 - 15:00",
                14.toLong()
            )
        )
        add(
            UISessionTime(
                "16:00 - 17:00",
                16.toLong()
            )
        )
    }

}