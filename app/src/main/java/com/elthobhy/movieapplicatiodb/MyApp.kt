package com.elthobhy.movieapplicatiodb

import android.app.Application
import com.elthobhy.movieapplicatiodb.di.useCase
import com.elthobhy.movieapplicatiodb.di.viewModel
import com.elthobhy.movieapplicationdb.core.di.database
import com.elthobhy.movieapplicationdb.core.di.networking
import com.elthobhy.movieapplicationdb.core.di.repository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                database,
                networking,
                repository,
                viewModel,
                useCase
            )
        }
    }
}