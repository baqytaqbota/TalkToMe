package com.example.talktome.domain.session

import com.example.talktome.common.baseUseCase.BaseUseCase
import com.example.talktome.common.network.networkUtils.ResultApi
import com.example.talktome.data.session.model.CreateSessionRequestBody
import com.example.talktome.data.session.model.SessionDateRequestBody
import com.example.talktome.data.session.model.SessionResultDTO
import com.example.talktome.data.session.repository.SessionRepository

class MakeCreateSessionUseCase(
    private val repo: SessionRepository
): BaseUseCase<CreateSessionRequestBody, Unit>(){

    override suspend fun run(
        params: CreateSessionRequestBody,
        response: (ResultApi<Unit>) -> Unit
    ) {
        response.invoke(
            repo.makeCreateSession(params)
        )
    }
}

class GetNearSessionUseCase(
    private val repo: SessionRepository
): BaseUseCase<Unit, SessionResultDTO>(){

    override suspend fun run(params: Unit, response: (ResultApi<SessionResultDTO>) -> Unit) {
        response.invoke(
            repo.getNearSession()
        )
    }
}

class GetAllSessionsUseCase(
    private val repo: SessionRepository
): BaseUseCase<Unit, List<SessionResultDTO>>(){

    override suspend fun run(params: Unit, response: (ResultApi<List<SessionResultDTO>>) -> Unit) {
        response.invoke(
            repo.getAllSessions(SessionDateRequestBody(""))
        )
    }
}

class GetSessionsByDateUseCase(
    private val repo: SessionRepository
): BaseUseCase<String, List<SessionResultDTO>>(){

    override suspend fun run(params: String, response: (ResultApi<List<SessionResultDTO>>) -> Unit) {
        response.invoke(
            repo.getSessionsByDate(SessionDateRequestBody(params))
        )
    }
}