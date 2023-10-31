package com.sdk.cinema2.core

fun lerpCin(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}
const val DEFAULT_VIDEO = "https://file-examples.com/storage/fe1134defc6538ed39b8efa/2017/04/file_example_MP4_640_3MG.mp4"