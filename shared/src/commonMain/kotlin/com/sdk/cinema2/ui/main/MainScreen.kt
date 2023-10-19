package com.sdk.cinema2.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sdk.cinema2.ui.helper.LocalSafeArea

@Composable
fun MainScreen(component: MainComponent) {
    Scaffold(
        modifier = Modifier.padding(LocalSafeArea.current)
    ) {
        Button(
            onClick = {
                component.onOutput(MainComponent.Output.OnMovieClicked(1))
            }
        ) {
            Text(text = "Main")
        }
    }
}