package com.cremasfood.app.ui.components.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cremasfood.app.R
import com.cremasfood.app.ui.color.OrangeMain
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(lat: Double, long: Double, onClickDeny: () -> Unit) {

    val circleRadius = 500.0
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(lat, long),
            18f
        )
    }

    BottomSheetTitle(title = stringResource(id = R.string.cremas_tv_maps_screen)) {
        onClickDeny.invoke()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {

        GoogleMap(
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            content = {
                Circle(
                    center = LatLng(-8.490515156510343, -56.86146077288239),
                    radius = circleRadius,
                    fillColor = OrangeMain.copy(alpha = 0.1f),
                    strokeColor = OrangeMain,
                    strokeWidth = 2.0f
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

    }
}