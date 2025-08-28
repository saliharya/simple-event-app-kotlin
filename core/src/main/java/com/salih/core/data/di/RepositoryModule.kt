package com.salih.core.data.di

import com.salih.core.data.repository.AuthRepositoryImpl
import com.salih.core.data.repository.EventRepositoryImpl
import com.salih.core.domain.repository.AuthRepository
import com.salih.core.domain.repository.EventRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
}