package com.sdk.cinema2.ui.detail

import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sdk.cinema2.ui.main.MainComponent

@Composable
fun DetailScreen(component: DetailComponent) {
    Scaffold {
        Button(
            onClick = {
                component.onOutput(DetailComponent.Output.NavigateBack)
            }
        ) {
            Text(text = "Detail")
        }
    }
}