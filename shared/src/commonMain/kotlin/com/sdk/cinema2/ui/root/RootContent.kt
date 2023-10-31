package com.sdk.cinema2.ui.root

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.sdk.cinema2.data.di.AppModule
import com.sdk.cinema2.ui.detail.DetailScreen
import com.sdk.cinema2.ui.main.MainScreen
import org.kodein.di.compose.withDI

@Composable
fun RootContent(component: RootComponent) = withDI(AppModule.di){
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme(
            background = Color.Black
        ) else lightColorScheme()
    ) {
        Children(
            stack = component.childStack,
            animation = stackAnimation(fade())
        ) {
            when(val child = it.instance) {
                is RootComponent.Child.Main -> MainScreen(child.component)
                is RootComponent.Child.Detail -> DetailScreen(child.component)
            }
        }
    }
}