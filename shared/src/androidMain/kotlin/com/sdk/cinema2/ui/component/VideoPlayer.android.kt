package com.sdk.cinema2.ui.component

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.sdk.cinema2.ui.component.Loading

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String,
    onBack: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var isTopBarVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val mediaItem = remember {
        MediaItem.Builder()
            .setUri(url)
            .build()
    }


    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            this.setMediaItem(mediaItem)
            this.prepare()
            this.playWhenReady = true

            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    isLoading = playbackState != Player.STATE_READY
                }
            })
        }
    }
    Box(
        modifier = modifier
            .background(Color.Black)
    ) {
        DisposableEffect(
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
                    StyledPlayerView(it).apply {
                        player = exoPlayer
                        keepScreenOn = true
                        setShowNextButton(false)
                        setShowPreviousButton(false)
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                        this.setControllerVisibilityListener { visible ->
                            isTopBarVisible = visible == 0
                        }
                    }
                }
            )
        ) {
            onDispose {
                exoPlayer.release()
            }
        }

        AnimatedVisibility(
            visible = isTopBarVisible,
            exit = fadeOut(),
            enter = fadeIn()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Black,
                                Color.Transparent
                            )
                        )
                    )
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp, top = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        exoPlayer.release()
                        onBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${mediaItem.mediaMetadata.title ?: "Title"}",
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontSize = 19.sp
                )
            }
        }
        if (isLoading) {
            Loading()
        }
    }
}