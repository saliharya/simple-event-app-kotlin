package com.salih.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salih.core.domain.model.EventEntity
import com.salih.core.domain.usecase.GetAllEventsUseCase
import com.salih.core.domain.usecase.InsertEventUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel(
    private val insertEventUseCase: InsertEventUseCase,
    private val getAllEventsUseCase: GetAllEventsUseCase
) : ViewModel() {

    private val _events = MutableStateFlow<List<EventEntity>>(emptyList())
    val events: StateFlow<List<EventEntity>> get() = _events

    fun insertEvent(event: EventEntity) {
        viewModelScope.launch {
            insertEventUseCase(event)
        }
    }

    fun fetchAllEvents() {
        viewModelScope.launch {
            getAllEventsUseCase().collect { list ->
                _events.value = list
            }
        }
    }
}
