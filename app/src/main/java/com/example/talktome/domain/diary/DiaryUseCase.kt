package com.example.talktome.domain.diary

import com.example.talktome.common.baseUseCase.BaseUseCase
import com.example.talktome.common.network.networkUtils.ResultApi
import com.example.talktome.data.diary.model.DiaryDTO
import com.example.talktome.data.diary.model.NewDiaryRequestDTO
import com.example.talktome.data.diary.repository.DiaryRepository
import com.example.talktome.data.doctors.model.DoctorListRequestBody

class NewDiaryUseCase(
    private val repo: DiaryRepository
) : BaseUseCase<NewDiaryRequestDTO, Unit>(){

    override suspend fun run(params: NewDiaryRequestDTO, response: (ResultApi<Unit>) -> Unit) {
        response.invoke(
            repo.addNewDiary(params)
        )
    }
}

class GetAllDiariesUseCase(
    private val repo: DiaryRepository
): BaseUseCase<Unit, List<DiaryDTO>>(){

    override suspend fun run(params: Unit, response: (ResultApi<List<DiaryDTO>>) -> Unit) {
        response.invoke(
            repo.getAllDiaries()
        )
    }

}