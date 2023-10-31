package com.sdk.cinema2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

@Composable
fun BoxShader(
    alpha: Float = .4f,
    content: @Composable () -> Unit,
) {
    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color.Green.copy(alpha = alpha), Color.Transparent),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 1f)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
    ) {
        content()
    }
}