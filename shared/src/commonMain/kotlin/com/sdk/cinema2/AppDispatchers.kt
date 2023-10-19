package com.sdk.cinema2

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
expect val appDispatchers: AppDispatchers