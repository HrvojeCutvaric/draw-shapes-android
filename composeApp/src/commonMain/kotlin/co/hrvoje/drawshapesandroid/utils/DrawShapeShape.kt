package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed interface DrawShapeShape {

    val name: StringResource

    val icon: DrawableResource

    var first: Offset?

    var second: Offset?

    fun DrawScope.draw()
}
