package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import co.hrvoje.drawshapesandroid.ui.Primary
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.circle
import drawshapesandroid.composeapp.generated.resources.ic_circle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Circle(
    override val name: StringResource = Res.string.circle,
    override val icon: DrawableResource = Res.drawable.ic_circle,
    override var first: Offset? = null,
    override var second: Offset? = null,
) : DrawShapeShape {

    override fun DrawScope.draw() {
        val first = first ?: return
        val second = second ?: return
        val radius = first.distanceTo(second)
        drawCircle(
            color = Primary,
            radius = radius,
            center = first,
            style = Stroke(width = 6f)
        )
    }
}
