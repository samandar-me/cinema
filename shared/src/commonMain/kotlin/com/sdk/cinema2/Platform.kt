package com.sdk.cinema2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform