package co.hrvoje.drawshapesandroid.viewmodel

import androidx.compose.ui.geometry.Offset
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape
import co.hrvoje.drawshapesandroid.utils.UndoAction

data class MainState(
    val shapes: List<DrawShapeShape>,
    val selectedShape: DrawShapeShape,
    val lastTap: Offset?,
    val drawnShapes: List<DrawShapeShape>,
    val undoActions: List<UndoAction>,
    val trashUndoActions: List<UndoAction>,
)
