package com.example.camera.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File

interface CameraLauncher {
    val capturedImageUri: Uri
    fun captureImage()
}

@Composable
fun rememberCameraLauncher(
    onResult: (Uri) -> Unit = {}
) : CameraLauncher {

    val ctx = LocalContext.current
    val imageUri = remember {
        val imageFile = File.createTempFile("tmp_image", ".jpg", ctx.externalCacheDir)
        FileProvider.getUriForFile(ctx, ctx.packageName + ".provider", imageFile)
    }

    var capturedImageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val cameraActivityLauncher =
        // SE la foto è stata scattata, allora assegna l'Uri che gli abbiamo passato
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { pictureTaken ->
            if (pictureTaken) {
                capturedImageUri = imageUri
                // Possiamo personalizzare il comportamento da fuori quando viene scattata una foto
                onResult(capturedImageUri)
            }

        }

    val cameraLauncher by remember {
        derivedStateOf {
            object : CameraLauncher {
                override val capturedImageUri = capturedImageUri

                // Gli stiamo dicendo di avviare la camera con Uri in cui andare a salvare la foto
                override fun captureImage() = cameraActivityLauncher.launch(imageUri)

            }
        }
    }

    return cameraLauncher
}