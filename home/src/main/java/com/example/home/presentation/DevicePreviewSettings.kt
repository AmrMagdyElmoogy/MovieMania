package com.example.home.presentation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

object DevicePreviewSettings {
    @Preview(
        showBackground = true,
        showSystemUi = true,
        locale = "en",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        name = "HomeScreen-English"
    )
    @Preview(
        showSystemUi = true,
        showBackground = true,
        locale = "ar",
        name = "HomeScreen-Arabic"
    )
    annotation class DevicePreview
}
