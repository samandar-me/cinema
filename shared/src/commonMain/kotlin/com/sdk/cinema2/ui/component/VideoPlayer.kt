package com.sdk.cinema2.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(
    modifier: Modifier,
    url: String,
    onBack: () -> Unit
)