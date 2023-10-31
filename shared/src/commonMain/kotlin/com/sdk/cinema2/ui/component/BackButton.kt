package com.sdk.cinema2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(45.dp).clip(CircleShape).background(Color.Black).padding(17.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(Icons.Default.ArrowBackIosNew,"", tint = Color.White)
        }
    }
}