package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset

sealed interface UndoAction {
    data class Tap(val offset: Offset) : UndoAction
    data class Shape(val shape: DrawShapeShape) : UndoAction
}
