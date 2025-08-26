package com.salih.core.data.mapper

import com.salih.core.data.model.dto.EventDto
import com.salih.core.domain.model.EventEntity

fun EventDto.toEntity() = EventEntity(
    id = id ?: 0L,
    name = name.orEmpty(),
    thumbnailUrl = thumbnailUrl.orEmpty(),
    startDateTime = startDateTime.orEmpty(),
    endDateTime = endDateTime.orEmpty(),
    organizer = organizer.orEmpty(),
    location = location.orEmpty(),
    description = description.orEmpty(),
)

fun EventEntity.toDto() = EventDto(
    id = id,
    name = name,
    thumbnailUrl = thumbnailUrl,
    startDateTime = startDateTime,
    endDateTime = endDateTime,
    organizer = organizer,
    location = location,
    description = description,
)
