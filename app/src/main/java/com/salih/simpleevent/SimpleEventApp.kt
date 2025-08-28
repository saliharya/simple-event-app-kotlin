package com.salih.simpleevent

import android.app.Application
import androidx.multidex.MultiDex
import com.salih.core.data.di.remoteModule
import com.salih.core.data.di.repositoryModule
import com.salih.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimpleEventApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@SimpleEventApp)
            modules(
                listOf(
                    remoteModule,
                    presentationModule,
                    repositoryModule,
                )
            )
        }
    }
}