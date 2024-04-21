package com.android.demo.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.demo.R

@Composable
fun ProfileSurface(
    fullname: String,
    username: String
) {
    Surface(
        onClick = { },
        modifier = Modifier.size(500.dp, 50.dp),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.goku),
                contentDescription = "Profile pic",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Column {
                Text(
                    text = fullname,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    username,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}