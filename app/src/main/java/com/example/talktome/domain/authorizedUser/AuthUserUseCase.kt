package com.example.talktome.domain.authorizedUser

import com.example.talktome.data.authorizedUserData.AuthUserRepository

class GetTokenUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun getToken() = authUserRepository.getToken()
}

class SetTokenUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun setToken(token: String) {
        authUserRepository.setToken(token)
    }
}

class GetUserRoleUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun getUserRole() = authUserRepository.getRole()
}

class SetUserRoleUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun setUserRole(userRole: String){
        authUserRepository.setRole(userRole)
    }
}

class GetUserIdUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun getUserId() = authUserRepository.getUserId()
}

class SetUserIdUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun setUserId(id: String) {
        authUserRepository.setUserId(id)
    }
}

class MakeClearUserDataUseCase(
    private val authUserRepository: AuthUserRepository
){
    fun clearUserData(){
        authUserRepository.clearUserData()
    }
}