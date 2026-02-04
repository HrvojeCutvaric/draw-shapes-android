package co.hrvoje.drawshapesandroid.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.hrvoje.drawshapesandroid.ui.DrawShapeTheme
import co.hrvoje.drawshapesandroid.ui.Primary
import co.hrvoje.drawshapesandroid.utils.Circle
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape
import co.hrvoje.drawshapesandroid.utils.Square
import co.hrvoje.drawshapesandroid.utils.Triangle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen() {

}

@Composable
private fun MainLayout(
    shapes: List<DrawShapeShape>,
    currentShape: DrawShapeShape?,
    onShapeSelected: (DrawShapeShape) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(
                shapes = shapes,
                currentShape = currentShape,
                onShapeSelected = onShapeSelected
            )
        }
    ) { paddingValues ->
        TapCanvas(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun TapCanvas(
    modifier: Modifier = Modifier,
) {
    var lastTap by remember { mutableStateOf<Offset?>(null) }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset -> lastTap = offset }
            }
    ) {
        lastTap?.let {
            drawCircle(
                color = Primary,
                radius = 12f,
                center = it
            )
        }
    }
}

@Composable
fun BottomBar(
    shapes: List<DrawShapeShape>,
    currentShape: DrawShapeShape?,
    onShapeSelected: (DrawShapeShape) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(vertical = 8.dp, horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        shapes.forEach {
            BottomBarItem(
                shape = it,
                isSelected = it == currentShape,
                onClick = { onShapeSelected(it) },
            )
        }
    }
}

@Composable
fun BottomBarItem(shape: DrawShapeShape, isSelected: Boolean, onClick: () -> Unit) {
    val color = if (isSelected) MaterialTheme.colorScheme.onSurface
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(shape.icon),
            contentDescription = null,
            tint = color,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(shape.name),
            style = MaterialTheme.typography.labelSmall.copy(
                color = color,
            ),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    val shapes = listOf(
        Circle(), Square(), Triangle()
    )

    DrawShapeTheme {
        MainLayout(
            shapes = listOf(
                Circle(), Square(), Triangle()
            ),
            currentShape = shapes[0],
            onShapeSelected = {},
        )
    }
}
