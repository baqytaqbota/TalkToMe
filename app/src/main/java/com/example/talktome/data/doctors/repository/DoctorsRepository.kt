package com.example.talktome.data.doctors.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall
import com.example.talktome.data.doctors.model.DoctorListRequestBody

class DoctorsRepository(private val apiService: ApiService) {

    suspend fun getAllDoctors(param: DoctorListRequestBody) = safeApiCall {
        apiService.getDoctors(param)
    }

    suspend fun getDoctorsByTag(param: DoctorListRequestBody) = safeApiCall {
        apiService.getDoctors(param)
    }

    suspend fun getDoctorProfile() = safeApiCall {
        apiService.getDoctorProfile()
    }

}