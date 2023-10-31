package com.sdk.cinema2.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.cinema2.PlatformName
import com.sdk.cinema2.getPlatform
import com.sdk.cinema2.ui.component.BoxShader
import com.sdk.cinema2.ui.component.MainShimmer
import com.sdk.cinema2.ui.component.MovieItem
import com.sdk.cinema2.ui.component.NetworkImage
import com.sdk.cinema2.ui.helper.LocalSafeArea

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(component: MainComponent) {
    val state by component.state.collectAsState()

    Scaffold  {
        if (state.isLoading) {
            MainShimmer(it)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(
                            start = 16.dp,
                            bottom = 16.dp,
                            top = if (getPlatform().name == PlatformName.IOS) 50.dp else 30.dp
                        ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cinema",
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                        )
                        IconButton(
                            onClick = {
                                //component.onEvent(MainStore.Intent.OnMovieSearch(""))
                            }
                        ) {
                            Icon(Icons.Rounded.Search, "search")
                        }
                    }
                }
                item {
                    BoxShader {
                        val pagerState = rememberPagerState(pageCount = { state.success.size })
                        HorizontalPager(
                            contentPadding = PaddingValues(horizontal = 80.dp),
                            state = pagerState,
                        ) { page ->
                            MovieItem(
                                movie = state.success[page],
                                pagerState = pagerState,
                                page = page,
                                onClick = {
                                    component.onOutput(MainComponent.Output.OnMovieClicked(state.success[page].id))
                                }
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = "For You",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.success2) { movie ->
                            NetworkImage(
                                url = movie.posterImage,
                                modifier = Modifier.clip(RoundedCornerShape(12.dp)).size(
                                    height = 190.dp,
                                    width = 120.dp
                                ).clickable {
                                    component.onOutput(MainComponent.Output.OnMovieClicked(movie.id))
                                }
                            )
                        }
                    }
                    Spacer(Modifier.height(30.dp))
                }
            }
        }
    }
}