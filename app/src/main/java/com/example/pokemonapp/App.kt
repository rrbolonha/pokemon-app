package com.example.pokemonapp

import android.app.Application
import com.example.pokemonapp.data.database.AppDatabase
import com.example.pokemonapp.data.di.dataModule
import com.example.pokemonapp.domain.di.domainModule
import com.example.pokemonapp.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
        initDatabase()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
        }
        loadKoinModules(presentationModule + domainModule + dataModule)
    }

    private fun initDatabase() {
        AppDatabase.getInstance(applicationContext)
    }
}