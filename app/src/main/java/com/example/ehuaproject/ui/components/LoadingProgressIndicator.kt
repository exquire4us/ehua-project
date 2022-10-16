package com.example.ehuaproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ehuaproject.R

@Composable
fun LoadingProgressIndicator(
    modifier: Modifier,
) {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_utensils))
    val lottieAnimationProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = true,
        speed = 1f,
        iterations = LottieConstants.IterateForever
    )
    Box(modifier = modifier
        .fillMaxSize()
        .clickable(enabled = true, onClick = {})
        .background(color = Color.Transparent)){
        LottieAnimation(
            composition = lottieComposition,
            progress = { lottieAnimationProgress },
            contentScale = ContentScale.Crop,
            renderMode = RenderMode.HARDWARE,
            alignment = Alignment.Center,
            modifier = Modifier.size(200.dp).align(Alignment.Center)
        )
    }
}