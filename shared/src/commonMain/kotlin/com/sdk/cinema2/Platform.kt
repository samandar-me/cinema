package com.sdk.cinema2

interface Platform {
    val name: PlatformName
}

expect fun getPlatform(): Platform

enum class PlatformName {
    ANDROID,IOS,DESKTOP
}