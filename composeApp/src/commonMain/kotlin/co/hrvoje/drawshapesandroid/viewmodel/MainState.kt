package co.hrvoje.drawshapesandroid.viewmodel

import androidx.compose.ui.geometry.Offset
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape

data class MainState(
    val shapes: List<DrawShapeShape>,
    val selectedShape: DrawShapeShape?,
    val lastTap: Offset?,
)
