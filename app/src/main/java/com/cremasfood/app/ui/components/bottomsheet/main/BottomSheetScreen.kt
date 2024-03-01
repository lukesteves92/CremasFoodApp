package com.cremasfood.app.ui.components.bottomsheet.main

import androidx.compose.runtime.Composable
import com.cremasfood.app.ui.components.bottomsheet.maps.MapsScreen

@Composable
fun SheetLayout(lat: Double, long: Double, onCloseBottomSheet: () -> Unit) {
    BottomSheetBody {
        MapsScreen(lat = lat, long = long, onClickDeny = {
            onCloseBottomSheet.invoke()
        })
    }
}

@Composable
fun BottomSheetBody(content: @Composable () -> Unit) = content()