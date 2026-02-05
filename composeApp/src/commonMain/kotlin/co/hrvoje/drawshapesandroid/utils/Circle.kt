package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import co.hrvoje.drawshapesandroid.ui.Primary
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.circle
import drawshapesandroid.composeapp.generated.resources.ic_circle
import drawshapesandroid.composeapp.generated.resources.tap_to_set_radius
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Circle(
    override val name: StringResource = Res.string.circle,
    override val icon: DrawableResource = Res.drawable.ic_circle,
    override val info: StringResource = Res.string.tap_to_set_radius,
    override var centerPoint: Offset? = null,
    override var referencePoint: Offset? = null,
) : DrawShapeShape {

    override fun DrawScope.draw() {
        val center = centerPoint ?: return
        val circumferencePoint = referencePoint ?: return
        val radius = center.distanceTo(circumferencePoint)
        drawCircle(
            color = Primary,
            radius = radius,
            center = center,
            style = Stroke(width = 6f)
        )
    }

    override fun createWithPoints(
        centerPoint: Offset,
        referencePoint: Offset,
    ): DrawShapeShape = Circle(
        centerPoint = centerPoint,
        referencePoint = referencePoint,
    )

    private fun Offset.distanceTo(other: Offset): Float = (this - other).getDistance()
}
