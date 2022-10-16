package com.example.ehuaproject.ui.screens.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ehuaproject.R
import com.example.ehuaproject.navigator.EhuaAppNavigator
import com.example.ehuaproject.ui.components.GeneralButton
import com.example.ehuaproject.ui.components.LargeTextComponent
import com.example.ehuaproject.ui.theme.EhuaAppTypography
import com.example.ehuaproject.ui.theme.Red100
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(navigator: EhuaAppNavigator){
    var playAnimation by remember{mutableStateOf(false)}
    val animationSpeed by remember{mutableStateOf(1.5f)}
    val scaleA by remember{
        mutableStateOf(Animatable(1000f))
    }
    val scaleB by remember{
        mutableStateOf(Animatable(-1000f))
    }

    LaunchedEffect(Unit) {
        val jobA = launch {
            scaleA.animateTo(targetValue = 500f, animationSpec = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
                )
            )
            scaleA.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        }
        val jobB = launch {
            scaleB.animateTo(targetValue = 500f, animationSpec = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            )
            )
            scaleB.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        }
        jobA.join()
        jobB.join()
        playAnimation = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Bottom,
    ) {
        LottieImage(
            modifier = Modifier.graphicsLayer(translationY = scaleB.value),
            animationSpeed = animationSpeed,
            isPlaying = playAnimation
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .graphicsLayer(translationY = scaleA.value ),
        ) {
            Text(
                text = stringResource(R.string._360k_premium_recipes),
                style = EhuaAppTypography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Red100.copy(alpha = 0.7f),
                    textAlign = TextAlign.Left,
                ),
            )
            LargeTextComponent(
                modifier = Modifier,
                text = stringResource(R.string.cook_like_a_chef),
                textSize = 50.sp
            )
            GeneralButton(
                modifier = Modifier.fillMaxWidth(),
                buttonText = stringResource(R.string.get_started),
                onClick = {
                    navigator.gotoLogin()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        }
    }


@Composable
private fun LottieImage(
    modifier: Modifier,
    isPlaying: Boolean = true,
    animationSpeed: Float,
    compositionSpec: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.ingrediants),

) {
    val composition by rememberLottieComposition(spec =  compositionSpec)
    val animationProgress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        isPlaying = isPlaying,
        speed = animationSpeed,
        restartOnPlay = true,
    )
    Column(modifier = modifier.height(200.dp)) {
        LottieAnimation(
            composition = composition,
            progress = { animationProgress },
            renderMode = RenderMode.HARDWARE,
            modifier = Modifier.background(Color.Black),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,

        )

    }
}