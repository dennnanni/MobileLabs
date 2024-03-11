package com.example.firstlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.firstlab.ui.theme.FirstLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstLabTheme {
                val config = LocalConfiguration.current;
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.size(
                        config.screenWidthDp.dp,
                        config.screenHeightDp.dp
                    ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffolding()
                }
            }
        }
    }




}
