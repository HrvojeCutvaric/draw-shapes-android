package co.hrvoje.drawshapesandroid.utils

import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.circle
import drawshapesandroid.composeapp.generated.resources.ic_circle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Circle : DrawShapeShape {
    override val name: StringResource
        get() = Res.string.circle
    override val icon: DrawableResource
        get() = Res.drawable.ic_circle
}
