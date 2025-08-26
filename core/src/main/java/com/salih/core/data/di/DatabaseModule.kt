package com.salih.core.data.di

import androidx.room.Room
import com.salih.core.data.local.database.EventRoomDatabase
import com.salih.core.domain.usecase.GetAllEventsUseCase
import com.salih.core.domain.usecase.InsertEventUseCase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { InsertEventUseCase(get()) }
    single { GetAllEventsUseCase(get()) }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("salih".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
                androidContext(),
                EventRoomDatabase::class.java,
                "event_database"
            ).fallbackToDestructiveMigration(false).openHelperFactory(factory).build()
    }

    single {
        get<EventRoomDatabase>().eventDao()
    }
}