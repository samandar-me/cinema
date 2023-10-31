package com.sdk.cinema2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter

@Composable
internal fun NetworkImage(
    url: String,
    contentScale: ContentScale = ContentScale.FillBounds,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter("https://image.tmdb.org/t/p/w500/$url")

    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "network image",
            contentScale = contentScale,
            modifier = modifier
        )
    }
}