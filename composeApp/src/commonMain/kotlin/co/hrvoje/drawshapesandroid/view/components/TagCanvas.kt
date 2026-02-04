package co.hrvoje.drawshapesandroid.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import co.hrvoje.drawshapesandroid.ui.Primary
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape

@Composable
fun TapCanvas(
    modifier: Modifier = Modifier,
    lastTap: Offset?,
    drawnShapes: List<DrawShapeShape>,
    onTap: (Offset) -> Unit,
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset -> onTap(offset) }
            }
    ) {
        drawnShapes.forEach { shape ->
            shape.run { draw() }
        }

        lastTap?.let {
            drawCircle(
                color = Primary,
                radius = 12f,
                center = it
            )
        }
    }
}
