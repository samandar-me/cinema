package com.sdk.cinema2.android

import android.app.Application
import com.sdk.cinema2.data.di.AppModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class BaseApp: Application(), DIAware{
    override val di: DI
        get() = DI.lazy {
            import(androidXModule(this@BaseApp))
            import(AppModule.appModule)
        }
}