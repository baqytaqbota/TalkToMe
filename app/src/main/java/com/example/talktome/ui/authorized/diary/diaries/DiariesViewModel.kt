package com.example.talktome.ui.authorized.diary.diaries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.diary.model.DiaryDTO
import com.example.talktome.domain.diary.GetAllDiariesUseCase

class DiariesViewModel(
    private val getAllDiariesUseCase: GetAllDiariesUseCase
) : BaseViewModel(){

    private val _diaries = MutableLiveData<List<DiaryDTO>>()
    val diaries: LiveData<List<DiaryDTO>>
        get() = _diaries

    fun getAllDiaries(){
        getAllDiariesUseCase.request(
            params = Unit,
            onResult = {
                _diaries.value = it
            },
            loading = _loading,
            error = _error
        )
    }

}