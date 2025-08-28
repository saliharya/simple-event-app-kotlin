package com.salih.core.domain.usecase

import com.salih.core.domain.model.EventEntity
import com.salih.core.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class GetAllEventsUseCase(private val repository: EventRepository) {
    operator fun invoke(): Flow<List<EventEntity>> = repository.getAllEvents()
}
