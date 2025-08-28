package com.salih.core.data.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class EventDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("start_date_time") val startDateTime: String?,
    @SerializedName("end_date_time") val endDateTime: String?,
    val organizer: String?,
    val location: String?,
    val description: String?,
)