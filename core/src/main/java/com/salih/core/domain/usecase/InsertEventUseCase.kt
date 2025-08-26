package com.salih.core.domain.usecase

import com.salih.core.domain.model.EventEntity
import com.salih.core.domain.repository.EventRepository

class InsertEventUseCase(private val repository: EventRepository) {
    suspend operator fun invoke(event: EventEntity) {
        repository.insertEvent(event)
    }
}
