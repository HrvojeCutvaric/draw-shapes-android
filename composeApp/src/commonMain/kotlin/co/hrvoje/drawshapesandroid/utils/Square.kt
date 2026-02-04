package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import co.hrvoje.drawshapesandroid.ui.Primary
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.ic_square
import drawshapesandroid.composeapp.generated.resources.square
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Square(
    override val name: StringResource = Res.string.square,
    override val icon: DrawableResource = Res.drawable.ic_square,
    override var first: Offset? = null,
    override var second: Offset? = null,
) : DrawShapeShape {

    override fun DrawScope.draw() {
        val centerPoint = first ?: return
        val cornerPoint = second ?: return

        val centerToCornerVector = cornerPoint - centerPoint
        val perpendicularVector = Offset(-centerToCornerVector.y, centerToCornerVector.x)

        val firstCorner = centerPoint + centerToCornerVector
        val secondCorner = centerPoint + perpendicularVector
        val thirdCorner = centerPoint - centerToCornerVector
        val fourthCorner = centerPoint - perpendicularVector

        val squarePath = Path().apply {
            moveTo(firstCorner.x, firstCorner.y)
            lineTo(secondCorner.x, secondCorner.y)
            lineTo(thirdCorner.x, thirdCorner.y)
            lineTo(fourthCorner.x, fourthCorner.y)
            close()
        }

        drawPath(squarePath, color = Primary, style = Stroke(width = 6f))
    }
}
