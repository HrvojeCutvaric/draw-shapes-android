package co.hrvoje.drawshapesandroid.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import co.hrvoje.drawshapesandroid.ui.Primary
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.ic_triangle
import drawshapesandroid.composeapp.generated.resources.triangle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Triangle(
    override val name: StringResource = Res.string.triangle,
    override val icon: DrawableResource = Res.drawable.ic_triangle,
    override var first: Offset? = null,
    override var second: Offset? = null,
) : DrawShapeShape {

    override fun DrawScope.draw() {
        val centerPoint = first ?: return
        val vertexPoint = second ?: return

        val centerToVertexVector = vertexPoint - centerPoint

        val firstVertex = centerPoint + centerToVertexVector
        val secondVertex = centerPoint + rotate(centerToVertexVector, 120f)
        val thirdVertex = centerPoint + rotate(centerToVertexVector, -120f)

        val trianglePath = Path().apply {
            moveTo(firstVertex.x, firstVertex.y)
            lineTo(secondVertex.x, secondVertex.y)
            lineTo(thirdVertex.x, thirdVertex.y)
            close()
        }

        drawPath(trianglePath, color = Primary, style = Stroke(width = 6f))
    }
}
