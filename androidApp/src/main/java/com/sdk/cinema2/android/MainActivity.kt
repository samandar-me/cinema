package com.sdk.cinema2.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sdk.cinema2.ui.root.RootComponent
import com.sdk.cinema2.ui.root.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootComponent = RootComponent(
            componentContext = defaultComponentContext(),
            storeFactory = DefaultStoreFactory()
        )
        setContent {
            RootContent(component = rootComponent)
        }
    }
}
