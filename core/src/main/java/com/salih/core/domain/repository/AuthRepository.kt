package com.salih.core.domain.repository

import com.salih.common.util.ResourceState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<ResourceState<Boolean>>
}