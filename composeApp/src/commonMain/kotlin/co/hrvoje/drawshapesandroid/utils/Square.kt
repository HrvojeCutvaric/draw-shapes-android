package co.hrvoje.drawshapesandroid.utils

import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.circle
import drawshapesandroid.composeapp.generated.resources.ic_circle
import drawshapesandroid.composeapp.generated.resources.ic_square
import drawshapesandroid.composeapp.generated.resources.square
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class Square : DrawShapeShape {
    override val name: StringResource
        get() = Res.string.square
    override val icon: DrawableResource
        get() = Res.drawable.ic_square
}
