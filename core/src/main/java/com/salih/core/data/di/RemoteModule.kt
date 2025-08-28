package com.salih.core.data.di

import com.google.gson.Gson
import com.salih.core.data.remote.AuthService
import com.salih.core.domain.usecase.IsLoggedInUseCase
import com.salih.core.domain.usecase.LoginUseCase
import com.salih.core.domain.usecase.LogoutUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { Gson() }

    single { LoginUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { IsLoggedInUseCase(get()) }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://skill-test.free.beeceptor.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        get<Retrofit>().create(AuthService::class.java)
    }
}