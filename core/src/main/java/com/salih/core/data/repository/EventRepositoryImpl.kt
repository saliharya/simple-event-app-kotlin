package com.salih.core.data.repository

import com.salih.core.data.local.database.EventDao
import com.salih.core.data.mapper.toDto
import com.salih.core.data.mapper.toEntity
import com.salih.core.domain.model.EventEntity
import com.salih.core.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class EventRepositoryImpl(
    private val eventDao: EventDao
) : EventRepository {

    override suspend fun insertEvent(event: EventEntity) {
        eventDao.insertEvent(event.toDto())
    }

    override fun getAllEvents(): Flow<List<EventEntity>> =
        kotlinx.coroutines.flow.flow {
            emit(eventDao.getAllEvents().map { it.toEntity() })
        }
}