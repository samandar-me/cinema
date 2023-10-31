package com.sdk.cinema2

class AndroidPlatform : Platform {
    override val name: PlatformName = PlatformName.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()