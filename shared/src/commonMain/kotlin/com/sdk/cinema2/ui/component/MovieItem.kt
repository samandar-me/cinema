package com.sdk.cinema2.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.cinema2.core.lerpCin
import com.sdk.cinema2.data.model.Movie
import kotlin.math.abs
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieItem(
    movie: Movie,
    pagerState: PagerState,
    page: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val pageOffset = ((pagerState.currentPage - page) + pagerState
        .currentPageOffsetFraction).absoluteValue
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .graphicsLayer {
                scaleY = lerpCin(
                    start = .76f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            url = movie.posterImage,
            modifier = Modifier
                .height(340.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onClick()
                }
                .graphicsLayer {
                    val scale = lerpCin(1f, 1.75f, pageOffset)
                    scaleX *= scale
                    scaleY *= scale
                    alpha = lerpCin(
                        start = 0.7f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = movie.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(6.dp))
        Row {
            repeat(movie.vote.toInt()) {
                Icon(
                    Icons.Default.Star,
                    "stacked",
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
