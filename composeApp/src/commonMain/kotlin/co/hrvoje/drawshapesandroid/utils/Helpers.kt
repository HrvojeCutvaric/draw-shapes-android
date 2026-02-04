package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun rotate(vector: Offset, degrees: Float): Offset {
    val rad = degrees * (PI.toFloat() / 180f)
    val cosRadians = cos(rad)
    val sinRadians = sin(rad)
    return Offset(
        x = vector.x * cosRadians - vector.y * sinRadians,
        y = vector.x * sinRadians + vector.y * cosRadians
    )
}

fun Offset.distanceTo(other: Offset): Float = (this - other).getDistance()
