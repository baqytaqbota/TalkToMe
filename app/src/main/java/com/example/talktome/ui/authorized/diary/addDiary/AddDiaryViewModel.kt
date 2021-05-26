package com.example.talktome.ui.authorized.diary.addDiary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talktome.common.baseUI.BaseViewModel
import com.example.talktome.data.diary.model.NewDiaryRequestDTO
import com.example.talktome.domain.diary.NewDiaryUseCase

class AddDiaryViewModel(
    private val newDiaryUseCase: NewDiaryUseCase
) : BaseViewModel(){

    private val _close = MutableLiveData<Unit>()
    val close: LiveData<Unit>
        get() = _close

    fun addDiary(title: String, description: String){
        newDiaryUseCase.request(
            params = NewDiaryRequestDTO(title, description),
            onResult = {
                _close.value = Unit
            },
            loading = _loading,
            error = _error
        )
    }

}