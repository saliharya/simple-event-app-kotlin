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

    private val _firstEvent = MutableStateFlow<EventEntity?>(null)
    val firstEvent: StateFlow<EventEntity?> get() = _firstEvent

    init {
        viewModelScope.launch {
            getAllEventsUseCase().collect { list ->
                if (list.isNotEmpty()) {
                    _firstEvent.value = list.first()
                    _events.value = list.drop(1)
                } else {
                    _firstEvent.value = null
                    _events.value = emptyList()
                }
            }
        }
    }

    fun insertEvent(event: EventEntity) {
        viewModelScope.launch {
            insertEventUseCase(event)
        }
    }
}
