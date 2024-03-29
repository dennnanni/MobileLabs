package com.example.traveldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.traveldiary.ui.screens.HomeScreen
import com.example.traveldiary.ui.screens.TravelDetailsScreen
import com.example.traveldiary.ui.theme.TravelDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val list: MutableList<String> = mutableListOf()
//                    for(i in 1..20) {
//                        list.add("Travel $i")
//                    }
//
//                    HomeScreen(list)
                    TravelDetailsScreen()
                }
            }
        }
    }
}
