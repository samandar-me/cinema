package com.sdk.cinema2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainShimmer(
    paddingValues: PaddingValues
) {
    Column(Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
        Row {
            repeat(4) {
                MovieCard(400, 200)
            }
        }
        Spacer(Modifier.height(10.dp))
        Box(modifier = Modifier.size(50.dp, 30.dp).clip(RoundedCornerShape(12.dp)).padding(16.dp).shimmerEffect())
        Spacer(Modifier.height(10.dp))
        Row {
            repeat(10) {
                MovieCard(190, 120)
            }
        }
    }
}

@Composable
private fun MovieCard(height: Int, width: Int) {
    Box(
        Modifier
            .height(height.dp)
            .width(width.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .shimmerEffect()
    )
}