package com.todaysnote.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.todaysnote.R
import com.todaysnote.navigation.TodaysNoteScreens
import com.todaysnote.ui.theme.PrimaryTextColor
import com.todaysnote.ui.theme.TitleColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
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
        navController.navigate(TodaysNoteScreens.HomeScreen.name)
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                LargeTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black,
                ), title = {
                    Text(
                        text = "Today'S Note",
                        fontFamily = FontFamily(Font(R.font.metropolisblack)),
                        fontSize = 35.sp,
                        color = TitleColor
                    )
                })
            },
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = it.calculateTopPadding() + 34.dp),
                text = "Capture Your Mind",
                color = PrimaryTextColor,
                fontSize = 16.sp
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.BottomEnd,
                painter = painterResource(id = R.drawable.splash_screen_background),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PreviewSplash() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}