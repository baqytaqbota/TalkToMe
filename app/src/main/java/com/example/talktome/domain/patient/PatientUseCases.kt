package com.example.talktome.domain.patient

import com.example.talktome.common.baseUseCase.BaseUseCase
import com.example.talktome.common.network.networkUtils.ResultApi
import com.example.talktome.data.patient.model.PatientInfoDTO
import com.example.talktome.data.patient.repository.PatientRepository

class GetPatientProfileUseCase(
    private val repo: PatientRepository
): BaseUseCase<Unit, PatientInfoDTO>(){

    override suspend fun run(params: Unit, response: (ResultApi<PatientInfoDTO>) -> Unit) {
        response.invoke(
            repo.getPatientProfile()
        )
    }
}