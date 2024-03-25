package com.example.architetturacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.architetturacompose.data.models.Theme
import com.example.architetturacompose.ui.screens.ThemeToggleScreen
import com.example.architetturacompose.ui.screens.ThemeToggleViewModel
import com.example.architetturacompose.ui.theme.ArchitetturaComposeTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = koinViewModel<ThemeToggleViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            ArchitetturaComposeTheme(
                darkTheme = when (state.theme) {
                    Theme.Light -> false
                    Theme.Dark -> true
                    Theme.System -> isSystemInDarkTheme()
                }
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThemeToggleScreen(state = state, changeTheme = viewModel::changeTheme)
                }
            }
        }
    }
}