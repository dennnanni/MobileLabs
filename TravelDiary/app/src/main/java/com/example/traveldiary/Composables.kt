package com.example.traveldiary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            if(title == "Travel Diary") {
                IconButton(onClick = { /*TODO redirect to search page*/ }) {
                    Icon(Icons.Default.Search, "Search")
                }
            }
            if(title != "Settings") {
                IconButton(onClick = { /*TODO redirect to settings*/ }) {
                    Icon(Icons.Default.Settings, "Settings")
                }
            }
        },

    )
}