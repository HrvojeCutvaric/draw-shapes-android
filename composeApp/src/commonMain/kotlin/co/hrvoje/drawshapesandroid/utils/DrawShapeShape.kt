package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed interface DrawShapeShape {

    val name: StringResource

    val icon: DrawableResource

    val info: StringResource

    var centerPoint: Offset?

    var referencePoint: Offset?

    fun DrawScope.draw()

    fun createWithPoints(
        centerPoint: Offset,
        referencePoint: Offset,
    ): DrawShapeShape
}
