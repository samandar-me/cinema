package com.sdk.cinema2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun DetailShimmer() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth().height(320.dp).clip(RoundedCornerShape(12.dp)).shimmerEffect())
        Spacer(Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(12.dp)).shimmerEffect())
        Spacer(Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth().height(220.dp).clip(RoundedCornerShape(12.dp)).shimmerEffect())
    }
}