package com.example.architetturacompose

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// Sorta di activity ma globale, ci sono gli stessi metodi del ciclo di vita dell'activity,
// siamo sicuri che questa classe venga richiamata
// Bisogna inserirla nel manifest perché android sappia che deve essere instanziata all'avvio
class ThemeToggleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            // Ci sono alcune funzioni che possono avere più contesti e in questo modo esplicitiamo
            // a quale contesto ci riferiamo con il this
            androidContext(this@ThemeToggleApplication)
            modules(appModule)
        }
    }

}