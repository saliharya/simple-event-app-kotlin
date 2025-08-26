package com.salih.core.domain.repository

import com.salih.core.domain.model.EventEntity
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun insertEvent(event: EventEntity)
    fun getAllEvents(): Flow<List<EventEntity>>
}