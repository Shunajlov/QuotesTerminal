package com.ihavenodomain.quotesterminal

import android.app.Application
import com.ihavenodomain.quotesterminal.di.InteractorModule
import com.ihavenodomain.quotesterminal.di.NetworkModule
import com.ihavenodomain.quotesterminal.di.RepositoryModule
import com.ihavenodomain.quotesterminal.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        assembleGraph()
    }

    private fun assembleGraph() {
        val graph = listOf(
            NetworkModule.module,
            ViewModelModule.module,
            RepositoryModule.module,
            InteractorModule.module
        )

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(graph)
        }
    }
}