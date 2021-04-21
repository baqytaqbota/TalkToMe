package com.example.talktome.data.patient.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall

class PatientRepository (private val apiService: ApiService){

    suspend fun getPatientProfile() = safeApiCall {
        apiService.getPatientProfile()
    }
}