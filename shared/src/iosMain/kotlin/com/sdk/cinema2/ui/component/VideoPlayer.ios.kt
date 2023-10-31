package com.sdk.cinema2.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.delay
import platform.AVFoundation.AVLayerVideoGravity
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.rate
import platform.AVFoundation.seekToTime
import platform.AVKit.AVPlayerViewController
import platform.CoreGraphics.CGRect
import platform.CoreMedia.CMTime
import platform.Foundation.NSURL
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCAGravityResizeAspect
import platform.QuartzCore.kCAGravityResizeAspectFill
import platform.QuartzCore.kCATransactionDisableActions
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIView
import platform.UIKit.removeFromParentViewController

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String,
    onBack: () -> Unit
) {
    val player = remember { AVPlayer(uRL = NSURL.URLWithString(url)!!) }
    var isLoading by remember { mutableStateOf(true) }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true

    playerLayer.player = player

    LaunchedEffect(Unit) {
        delay(2000L)
        //isLoading = false
    }

    Box(
        modifier = Modifier.padding(top = 38.dp)
    ) {
        UIKitView(
            modifier = modifier.fillMaxSize(),
            factory = {
                val playerContainer = UIView()
                playerContainer.addSubview(avPlayerViewController.view)
                playerContainer
            },
            onResize = { view: UIView, rect: CValue<CGRect> ->
                CATransaction.begin()
                CATransaction.setValue(true, kCATransactionDisableActions)
                view.layer.setFrame(rect)
                playerLayer.setFrame(rect)
                avPlayerViewController.view.layer.frame = rect
                CATransaction.commit()
            },
            update = {
                isLoading = false
                player.play()
                avPlayerViewController.player?.play()
            }
        )
        BackButton {
            player.rate = 0f
            player.pause()
            avPlayerViewController.player = null
            avPlayerViewController.removeFromParentViewController()
            playerLayer.removeFromSuperlayer()
            onBack()
        }
    }
}