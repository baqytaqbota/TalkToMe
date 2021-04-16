package com.example.talktome

import android.app.Application
import com.example.talktome.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(viewModel)
        }
    }
}