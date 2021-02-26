package com.example.rickandmortyandroid

import android.app.Application
import com.example.rickandmortyandroid.di.*
import com.example.rickandmortyandroid.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndmortyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@RickAndmortyApp)
            modules(
                appModules,
                retrofitModule,
                roomModules,
                viewModelModules,
            )
        }
    }
}