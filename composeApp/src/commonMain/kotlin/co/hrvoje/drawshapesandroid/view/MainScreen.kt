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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import co.hrvoje.drawshapesandroid.ui.DrawShapeTheme
import co.hrvoje.drawshapesandroid.ui.Primary
import co.hrvoje.drawshapesandroid.utils.Circle
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape
import co.hrvoje.drawshapesandroid.utils.Square
import co.hrvoje.drawshapesandroid.utils.Triangle
import co.hrvoje.drawshapesandroid.view.components.ChipButton
import co.hrvoje.drawshapesandroid.viewmodel.MainAction
import co.hrvoje.drawshapesandroid.viewmodel.MainState
import co.hrvoje.drawshapesandroid.viewmodel.MainViewModel
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.ic_redo
import drawshapesandroid.composeapp.generated.resources.ic_undo
import drawshapesandroid.composeapp.generated.resources.redo
import drawshapesandroid.composeapp.generated.resources.undo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainLayout(
        state = state,
        onAction = viewModel::execute,
    )
}

@Composable
private fun MainLayout(
    state: MainState,
    onAction: (MainAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val isUndoEnabled = remember(state.undoActions) { state.undoActions.isNotEmpty() }
            val isRedoVisible =
                remember(state.trashUndoActions) { state.trashUndoActions.isNotEmpty() }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(16.dp))

                ChipButton(
                    isEnabled = isUndoEnabled,
                    label = stringResource(Res.string.undo),
                    iconRes = Res.drawable.ic_undo,
                    borderColor = MaterialTheme.colorScheme.outline,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    contentColor = if (isUndoEnabled) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline,
                    onButtonClicked = { onAction(MainAction.OnUndoClicked) },
                )

                if (isRedoVisible) {
                    Spacer(modifier = Modifier.width(16.dp))

                    ChipButton(
                        label = stringResource(Res.string.redo),
                        iconRes = Res.drawable.ic_redo,
                        borderColor = MaterialTheme.colorScheme.outline,
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        onButtonClicked = { onAction(MainAction.OnRedoClicked) }
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        bottomBar = {
            BottomBar(
                shapes = state.shapes,
                currentShape = state.selectedShape,
                onShapeSelected = { onAction(MainAction.OnShapeSelected(it)) }
            )
        }
    ) { paddingValues ->
        TapCanvas(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            lastTap = state.lastTap,
            drawnShapes = state.drawnShapes,
            onTap = { onAction(MainAction.OnTap(it)) }
        )
    }
}

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
            state = MainState(
                shapes = shapes,
                selectedShape = shapes.first(),
                lastTap = null,
                drawnShapes = emptyList(),
                undoActions = emptyList(),
                trashUndoActions = emptyList(),
            ),
            onAction = {}
        )
    }
}
