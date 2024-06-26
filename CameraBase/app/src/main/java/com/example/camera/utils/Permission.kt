package com.example.camera.utils

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

// Un permesso può essere richiesto massimo due volte prima che venga considerato permanentemente negato
enum class PermissionStatus {
    Granted,
    Denied,
    PermanentlyDenied,
    Unknown;

    val isDenied get() = this == Denied || this == PermanentlyDenied
    val isGranted get() = this == Granted
}

interface PermissionHandler {
    val permission: String
    val status: PermissionStatus
    fun launchPermissionRequest()
}

@Composable
fun rememberPermission(
    permission: String,
    onResult: (PermissionStatus) -> Unit = {}
) : PermissionHandler {
    var status by remember { mutableStateOf(PermissionStatus.Unknown) }

    val activity = LocalContext.current as ComponentActivity

    var permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        isGranted ->
        status = when {
            isGranted -> PermissionStatus.Granted
            activity.shouldShowRequestPermissionRationale(permission) -> PermissionStatus.Denied
            else -> PermissionStatus.PermanentlyDenied
        }
        onResult(status)
    }

    val permissionHandler by remember {
        derivedStateOf {
            object : PermissionHandler {
                override val permission = permission
                override val status = status

                override fun launchPermissionRequest() = permissionLauncher.launch(permission)
            }
        }
    }

    return permissionHandler
}