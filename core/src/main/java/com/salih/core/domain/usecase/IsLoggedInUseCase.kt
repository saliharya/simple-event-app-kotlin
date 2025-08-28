package com.salih.core.domain.usecase

import com.salih.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class IsLoggedInUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Boolean> = repository.isLoggedInFlow()
}