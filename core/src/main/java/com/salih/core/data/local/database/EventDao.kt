package com.salih.core.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salih.core.data.model.dto.EventDto


@Dao
interface EventDao {

    @Query("SELECT * FROM EventDto ORDER BY startDateTime ASC")
    suspend fun getAllEvents(): List<EventDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventDto)
}