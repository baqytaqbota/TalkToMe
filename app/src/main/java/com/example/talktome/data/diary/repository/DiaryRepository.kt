package com.example.talktome.data.diary.repository

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.networkUtils.safeApiCall
import com.example.talktome.data.diary.model.NewDiaryRequestDTO

class DiaryRepository(
    private val apiService: ApiService
){

    suspend fun addNewDiary(body: NewDiaryRequestDTO) = safeApiCall{
        apiService.addNewDiary(body)
    }

    suspend fun getAllDiaries() = safeApiCall {
        apiService.getAllDiaries()
    }
}