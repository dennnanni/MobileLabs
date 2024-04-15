package com.example.gps.utils

import android.content.Context
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings

enum class MonitoringStatus {
    Monitoring,
    Paused,
    NotMonitoring
}

data class Coordinates(val latitude: Double, val longitude: Double)

class LocationService(private val ctx: Context) {
    var coordinates: Coordinates? by mutableStateOf(null)
        private set
    var monitoringStatus by mutableStateOf(MonitoringStatus.NotMonitoring)
        private set
    var isLocationEnabled: Boolean? by mutableStateOf(null)
        private set

    val locationProviderClient = LocationServices.getFusedLocationProviderClient(ctx)
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).apply {
        setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
    }.build()
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            coordinates = Coordinates(p0.locations.last().latitude, p0.locations.last().longitude)
            endLocationRequest()
        }
    }

    fun openLocationSettings() {
        val intent = Intent(Settings.
        ACTION_LOCATION_SOURCE_SETTINGS).
        apply {
            flags = Intent.
            FLAG_ACTIVITY_NEW_TASK
        }
        if (intent.resolveActivity(ctx.
            packageManager) != null) {
            ctx.startActivity(intent)
        }
    }

    fun requestCurrentLocation() {
        // Controlla se la location è attiva
        val locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isLocationEnabled != true) return

        // Controlla se abbiamo il permesso
        val permissionGranted = ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (!permissionGranted) return

        locationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        monitoringStatus = MonitoringStatus.Monitoring
    }

    fun pauseLocationRequest() {
        if (monitoringStatus != MonitoringStatus.Monitoring) return

        locationProviderClient.removeLocationUpdates(locationCallback)
        monitoringStatus = MonitoringStatus.Paused
    }

    fun resumeLocationRequest() {
        if (monitoringStatus != MonitoringStatus.Paused) return

        requestCurrentLocation()
    }

    fun endLocationRequest() {
        if (monitoringStatus == MonitoringStatus.NotMonitoring) return
        locationProviderClient.removeLocationUpdates(locationCallback)
        monitoringStatus = MonitoringStatus.NotMonitoring
    }
}