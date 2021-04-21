package com.example.talktome.domain.authorization

import com.example.talktome.common.baseUseCase.BaseUseCase
import com.example.talktome.common.network.networkUtils.ResultApi
import com.example.talktome.data.authorization.model.AuthResultDTO
import com.example.talktome.data.authorization.model.LoginRequestBody
import com.example.talktome.data.authorization.model.RegisterRequestBody
import com.example.talktome.data.authorization.repository.AuthorizationRepository

class MakeLoginUseCase(
    private val repo: AuthorizationRepository
) : BaseUseCase<LoginRequestBody, AuthResultDTO>() {


    override suspend fun run(
        params: LoginRequestBody,
        response: (ResultApi<AuthResultDTO>) -> Unit
    ) {
        response.invoke(
            repo.makeLogin(params)
        )
    }

}

class MakeRegisterUseCase(
    private val repo: AuthorizationRepository
): BaseUseCase<RegisterRequestBody, AuthResultDTO>() {

    override suspend fun run(
        params: RegisterRequestBody,
        response: (ResultApi<AuthResultDTO>) -> Unit
    ) {
        response.invoke(
            repo.makeRegister(params)
        )
    }
}