package com.salih.core.domain.usecase

import com.salih.common.util.ResourceState
import com.salih.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Flow<ResourceState<Boolean>> {
        return repository.login(username, password)
    }
}