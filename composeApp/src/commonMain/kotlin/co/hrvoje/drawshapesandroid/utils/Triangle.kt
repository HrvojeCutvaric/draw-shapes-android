package co.hrvoje.drawshapesandroid.utils

import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.circle
import drawshapesandroid.composeapp.generated.resources.ic_circle
import drawshapesandroid.composeapp.generated.resources.ic_triangle
import drawshapesandroid.composeapp.generated.resources.triangle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Triangle : DrawShapeShape {
    override val name: StringResource
        get() = Res.string.triangle
    override val icon: DrawableResource
        get() = Res.drawable.ic_triangle
}
