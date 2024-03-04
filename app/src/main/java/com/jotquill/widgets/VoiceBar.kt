package com.jotquill.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jotquill.components.lerpF
import com.jotquill.components.toPxf
import com.jotquill.ui.theme.HardBeige
import kotlin.random.Random

@Composable
fun VoiceBar(
    modifier: Modifier = Modifier,
    barWidth: Dp = 2.dp,
    gapWidth: Dp = 2.dp,
    barColor: Color = HardBeige,
    isAnimating: Boolean = false,
) {
    val infiniteAnimation = rememberInfiniteTransition()
    val animations = mutableListOf<State<Float>>()
    val random = remember { Random(System.currentTimeMillis()) }

    repeat(15) {
        val durationMillis = random.nextInt(500, 2000)
        animations += infiniteAnimation.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis),
                repeatMode = RepeatMode.Reverse,
            )
        )
    }

    val barWidthFloat by rememberUpdatedState(newValue = barWidth.toPxf())
    val gapWidthFloat by rememberUpdatedState(newValue = gapWidth.toPxf())

    val initialMultipliers = remember {
        mutableListOf<Float>().apply {
            repeat(MaxLinesCount) { this += random.nextFloat() }
        }
    }

    val heightDivider by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 6f,
        animationSpec = tween(1000, easing = LinearEasing)
    )

    Canvas(modifier = modifier) {
        val canvasHeight = size.height
        val canvasWidth = size.width
        val canvasCenterY = canvasHeight / 2f

        val count =
            (canvasWidth / (barWidthFloat + gapWidthFloat)).toInt().coerceAtMost(MaxLinesCount)
        val animatedVolumeWidth = count * (barWidthFloat + gapWidthFloat)
        var startOffset = (canvasWidth - animatedVolumeWidth) / 2

        val barMinHeight = 0f
        val barMaxHeight = canvasHeight / 2f / heightDivider

        repeat(count) { index ->
            val currentSize = animations[index % animations.size].value
            var barHeightPercent = initialMultipliers[index] + currentSize
            if (barHeightPercent > 1.0f) {
                val diff = barHeightPercent - 1.0f
                barHeightPercent = 1.0f - diff
            }
            val barHeight = lerpF(barMinHeight, barMaxHeight, barHeightPercent)

            drawLine(
                color = barColor,
                start = Offset(startOffset, canvasCenterY - barHeight / 2),
                end = Offset(startOffset, canvasCenterY + barHeight / 2),
                strokeWidth = barWidthFloat,
                cap = StrokeCap.Round,
            )
            startOffset += barWidthFloat + gapWidthFloat
        }
    }
}


const val MaxLinesCount = 100



@Preview
@Composable
fun AnimatedVolumeLevelBarPreview(){
    VoiceBar(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(48.dp),
        barWidth = 3.dp,
        gapWidth = 2.dp,
        isAnimating = true,
    )
}