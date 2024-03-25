package com.example.architetturacompose

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.architetturacompose.data.repositories.ThemeRepository
import com.example.architetturacompose.ui.screens.ThemeToggleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("theme")

val appModule = module {
    // DataStore
    single { get<Context>().dataStore }

    // Repository
    single { ThemeRepository(get()) }


    // ViewModel
    viewModel { ThemeToggleViewModel(get()) }
}