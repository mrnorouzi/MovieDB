package com.mrn.moviedb

import android.app.Application
import com.mrn.moviedb.di.appModule
import com.mrn.moviedb.di.networkModule
import com.mrn.moviedb.di.repositoryModule
import com.mrn.moviedb.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieDBApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
//            androidLogger()
            androidContext(this@MovieDBApp.applicationContext)
            modules(listOf(appModule, networkModule, repositoryModule, uiModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}