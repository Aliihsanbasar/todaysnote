package com.jotquill.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jotquill.R
import com.jotquill.navigation.JotQuillScreens
import com.jotquill.ui.theme.SoftBeige
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f, animationSpec = tween(durationMillis = 500, easing = {
                OvershootInterpolator(5f).getInterpolation(it)
            })
        )

        delay(1000L)
        navController.navigate(JotQuillScreens.HomeScreen.name)
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftBeige),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier.height(125.dp).width(125.dp),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PreviewSplash() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}