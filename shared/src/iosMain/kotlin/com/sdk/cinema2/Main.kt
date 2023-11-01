package com.sdk.cinema2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sdk.cinema2.ui.helper.LocalSafeArea
import com.sdk.cinema2.ui.root.RootComponent
import com.sdk.cinema2.ui.root.RootContent
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val rootComponent = RootComponent(
        componentContext = DefaultComponentContext(
            LifecycleRegistry()
        ),
        storeFactory = DefaultStoreFactory()
    )
    return ComposeUIViewController {
        RootContent(rootComponent)
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = Color.Black
//        ) {
//
//        }
    }
}