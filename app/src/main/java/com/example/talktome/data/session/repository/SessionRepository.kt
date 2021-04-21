package com.example.talktome.data.session.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall
import com.example.talktome.data.session.model.CreateSessionRequestBody
import com.example.talktome.data.session.model.SessionDateRequestBody

class SessionRepository (private val apiService: ApiService) {

    suspend fun makeCreateSession(param: CreateSessionRequestBody) = safeApiCall {
        apiService.makeCreateSession(param)
    }

    suspend fun getNearSession() = safeApiCall {
        apiService.getNearlySession()
    }

    suspend fun getAllSessions(param: SessionDateRequestBody) = safeApiCall {
        apiService.getSessions(param)
    }

    suspend fun getSessionsByDate(param: SessionDateRequestBody) = safeApiCall {
        apiService.getSessions(param)
    }

}