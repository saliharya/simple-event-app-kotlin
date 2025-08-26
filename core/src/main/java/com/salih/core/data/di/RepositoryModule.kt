package com.salih.core.data.di

import com.salih.core.data.repository.AuthRepositoryImpl
import com.salih.core.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}