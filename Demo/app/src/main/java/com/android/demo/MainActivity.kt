package com.android.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.demo.ui.DemoNavGraph
import com.android.demo.ui.DemoRoute
import com.android.demo.ui.composables.BottomBar
import com.android.demo.ui.composables.TopBar
import com.android.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Creates a navController which will be saved across multiple creations
                    val navController = rememberNavController()
                    // Creates an observable on the backstack entry
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute by remember {
                        derivedStateOf {
                            DemoRoute.routes.find {
                                it.route == backStackEntry?.destination?.route
                            } ?: DemoRoute.HomePage
                        }
                    }

                    Scaffold(
                        topBar = { TopBar(navController, currentRoute, currentRoute.title) },
                        bottomBar = {
                            if (currentRoute.route != DemoRoute.Settings.route) { BottomBar(navController) }
                        }
                    ) { paddingValues ->
                        DemoNavGraph(
                            navController = navController,
                            modifier = Modifier.padding(paddingValues)
                        )

                    }
                }
            }
        }
    }
}