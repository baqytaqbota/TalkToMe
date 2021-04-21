package com.example.talktome.data.authorization.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall
import com.example.talktome.data.authorization.model.LoginRequestBody
import com.example.talktome.data.authorization.model.RegisterRequestBody

class AuthorizationRepository(private val apiService: ApiService){

    suspend fun makeLogin(data: LoginRequestBody) = safeApiCall {
        apiService.makeLogin(data)
    }

    suspend fun makeRegister(data: RegisterRequestBody) = safeApiCall {
        apiService.makeRegister(data)
    }

}