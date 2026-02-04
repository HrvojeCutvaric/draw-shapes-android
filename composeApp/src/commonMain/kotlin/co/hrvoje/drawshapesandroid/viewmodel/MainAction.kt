package co.hrvoje.drawshapesandroid.viewmodel

import androidx.compose.ui.geometry.Offset
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape

sealed interface MainAction {

    data class OnShapeSelected(val shape: DrawShapeShape) : MainAction
    data class OnTap(val offset: Offset) : MainAction
}
