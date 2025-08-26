package com.salih.simpleevent

import android.app.Application
import androidx.multidex.MultiDex
import com.salih.presentation.module.presentationModule
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
                    presentationModule,
                )
            )
        }
    }
}