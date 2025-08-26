package com.salih.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salih.core.data.model.dto.EventDto

@Database(entities = [EventDto::class], version = 1)
abstract class EventRoomDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}