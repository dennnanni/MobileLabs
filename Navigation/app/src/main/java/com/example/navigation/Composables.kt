package com.example.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavHostController) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val title = when (backStackEntry?.destination?.route) {
        NavigationRoute.Screen1.route -> "First screen"
        NavigationRoute.Screen2.route -> "Second screen"
        NavigationRoute.Screen3.route -> "Third screen"
        else -> ""
    }


    TopAppBar(title = { Text(title) },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, "Go Back")
                }
            }
        })
}

@Composable
fun Screen1(navController: NavHostController) {
    Row {
        Button(onClick = { navController.navigate(NavigationRoute.Screen2.route) }) {
            Text("Screen2")
        }
        Button(onClick = { navController.navigate(NavigationRoute.Screen3.route) }) {
            Text("Indietro")
        }

    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        Button(onClick = { navController.navigate(NavigationRoute.Screen3.route) },
            modifier = Modifier.weight(1F)
        ) {
            Text("Screen3")
        }
        Button(onClick = { navController.navigate(NavigationRoute.Screen1.route) },
            modifier = Modifier.weight(1F)
        ) {
            Text("Indietro")
        }
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Row {
        Button(onClick = { navController.navigate(NavigationRoute.Screen1.route) }) {
            Text("Screen1")
        }
        Button(onClick = { navController.navigate(NavigationRoute.Screen2.route) }) {
            Text("Indietro")
        }
    }
}
