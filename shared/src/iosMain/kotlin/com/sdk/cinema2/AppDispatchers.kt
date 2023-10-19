package com.sdk.cinema2

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val appDispatchers: AppDispatchers = object : AppDispatchers {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}