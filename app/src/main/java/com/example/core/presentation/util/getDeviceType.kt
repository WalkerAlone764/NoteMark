@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.example.core.presentation.util

import android.content.res.Configuration
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.core.presentation.util.OrientationType.*

@Composable
fun getDeviceType(): DeviceType {

    val orientationType = getOrientationType()
    val activity = LocalActivity.current

    val windowSizeClass = activity?.let { calculateWindowSizeClass(it) }

    if (windowSizeClass == null) {
        return DeviceType.MOBILE_PORTRAIT
    }

   val deviceType = if (orientationType == OrientationType.PORTRAIT) {
       return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
           DeviceType.MOBILE_PORTRAIT
       } else {
           DeviceType.TABLET_PORTRAIT
       }
   } else {
       return DeviceType.LANDSCAPE
   }

    return deviceType
}