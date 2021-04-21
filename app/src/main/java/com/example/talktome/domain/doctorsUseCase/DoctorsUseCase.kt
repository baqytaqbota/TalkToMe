package com.example.talktome.domain.doctorsUseCase

import com.example.talktome.common.baseUseCase.BaseUseCase
import com.example.talktome.common.network.networkUtils.ResultApi
import com.example.talktome.data.doctors.model.DoctorListDTO
import com.example.talktome.data.doctors.model.DoctorListRequestBody
import com.example.talktome.data.doctors.repository.DoctorsRepository

class GetAllDoctorsUseCase(
    private val repo: DoctorsRepository
) : BaseUseCase<Unit, List<DoctorListDTO>>() {

    override suspend fun run(
        params: Unit,
        response: (ResultApi<List<DoctorListDTO>>) -> Unit
    ) {
        response.invoke(
            repo.getAllDoctors(DoctorListRequestBody(emptyList()))
        )
    }
}

class GetDoctorsByTagUseCase(
    private val repo: DoctorsRepository
) : BaseUseCase<List<String>, List<DoctorListDTO>>() {

    override suspend fun run(
        params: List<String>,
        response: (ResultApi<List<DoctorListDTO>>) -> Unit
    ) {
        response.invoke(
            repo.getDoctorsByTag(
                DoctorListRequestBody(params)
            )
        )
    }
}

class GetDoctorProfileUseCase(
    private val repo: DoctorsRepository
): BaseUseCase<Unit, DoctorListDTO>(){

    override suspend fun run(params: Unit, response: (ResultApi<DoctorListDTO>) -> Unit) {
        response.invoke(
            repo.getDoctorProfile()
        )
    }
}