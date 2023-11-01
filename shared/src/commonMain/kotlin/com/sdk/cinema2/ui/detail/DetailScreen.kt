package com.sdk.cinema2.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.cinema2.core.DEFAULT_VIDEO
import com.sdk.cinema2.ui.component.BoxShader
import com.sdk.cinema2.ui.component.DetailShimmer
import com.sdk.cinema2.ui.component.VideoPlayer

@Composable
fun DetailScreen(component: DetailComponent) {
    val state by component.state.collectAsState()
    Scaffold {
        if (state.isLoading) {
            DetailShimmer()
        }
        state.success?.let { movie ->
            BoxShader(
                alpha = .2f
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    VideoPlayer(
                        modifier = Modifier.height(330.dp),
                        url = DEFAULT_VIDEO,
                        onBack = {
                            component.onOutput(DetailComponent.Output.NavigateBack)
                        }
                    )
                    Text(
                        text = movie.title,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(12.dp)
                    )
                    Text(
                        text = movie.genres[0].name,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TwoText(
                            title = movie.releaseDate,
                            hint = "date"
                        )
                        TwoText(
                            title = movie.vote.toString(),
                            hint = "vote"
                        )
                        TwoText(
                            title = movie.popularity.toString(),
                            hint = "popularity"
                        )
                    }
                    Text(
                        text = movie.overview,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Production Companies",
                        modifier = Modifier.padding(12.dp),
                        fontSize = 20.sp
                    )
                    LazyRow {
                        items(movie.companies) {
                            CompanyItem(it)
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun TwoText(
    title: String,
    hint: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = hint,
            fontSize = 13.sp,
            color = Color.LightGray
        )
    }
}
