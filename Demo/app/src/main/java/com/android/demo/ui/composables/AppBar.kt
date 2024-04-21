package com.android.demo.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.demo.R
import com.android.demo.ui.DemoRoute
import com.android.demo.ui.theme.Pink
import com.android.demo.ui.theme.PinkLight
import com.android.demo.ui.theme.Yellow

@Composable
fun BottomBar(
    navController: NavHostController
) {
    BottomAppBar(
        containerColor = Yellow,
        modifier = Modifier.clip(RoundedCornerShape(
            50.dp, 50.dp, 0.dp, 0.dp
        )),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.padding())

                // Icon button for menu
                IconButton(
                    onClick = { /* Handle menu click */ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(PinkLight)
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.Black)
                }

                Spacer(Modifier.padding())


                // Floating action button
                Surface(
                    modifier = Modifier.size(65.dp),
                    shape = CircleShape,
                    color = Pink,
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        IconButton(
                            onClick = { navController.navigate(DemoRoute.CreateTransaction.route) },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.padding())

                // Icon button for user
                IconButton(onClick = { /* Handle user click */ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(PinkLight)
                ) {
                    Icon(Icons.Filled.Person,
                        contentDescription = "User",
                        tint = Color.Black)
                }

                Spacer(Modifier.padding())


            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    currentRoute: DemoRoute,
    title: String = ""
) {
    TopAppBar(
        title = {
            if (currentRoute.route == DemoRoute.HomePage.route){
                ProfileSurface("Full Name", "@username")
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Pink,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        },
        actions = {
            if (currentRoute.route == DemoRoute.HomePage.route){
                IconButton({ navController.navigate(DemoRoute.Settings.route) }) {
                    Icon(Icons.Filled.Settings, "Go to settings")
                }
            }
        }
    )
}