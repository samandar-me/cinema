package com.sdk.cinema2.core

fun lerpCin(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}
const val DEFAULT_VIDEO = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"