package com.sdk.cinema2

class IOSPlatform: Platform {
    override val name: PlatformName = PlatformName.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()