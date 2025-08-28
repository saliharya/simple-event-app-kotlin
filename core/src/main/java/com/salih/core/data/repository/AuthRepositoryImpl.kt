package com.salih.core.data.repository

import com.salih.common.util.ResourceState
import com.salih.core.data.local.datastore.DataStoreManager
import com.salih.core.data.model.request.LoginRequest
import com.salih.core.data.remote.AuthService
import com.salih.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dataStoreManager: DataStoreManager
) : AuthRepository {

    override suspend fun login(username: String, password: String): Flow<ResourceState<Boolean>> =
        flow {
            emit(ResourceState.Loading())
            try {
                val response = authService.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.code == "00") {
                        dataStoreManager.setLoginStatus(true)
                        emit(ResourceState.Success(true))
                    } else {
                        dataStoreManager.setLoginStatus(false)
                        emit(ResourceState.Success(false))
                    }
                } else {
                    emit(ResourceState.Error("HTTP error: ${response.code()}"))
                }
            } catch (e: Exception) {
                emit(ResourceState.Error(e.message ?: "Unexpected error"))
            }
        }

    override suspend fun logout() {
        dataStoreManager.clearLoginStatus()
    }

    override fun isLoggedInFlow(): Flow<Boolean> = dataStoreManager.isLoggedInFlow
}

