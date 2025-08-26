package com.salih.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventEntity(
    val id: Long,
    val name: String,
    val thumbnailUrl: String,
    val startDateTime: String,
    val endDateTime: String,
    val organizer: String,
    val location: String,
    val description: String,
) : Parcelable