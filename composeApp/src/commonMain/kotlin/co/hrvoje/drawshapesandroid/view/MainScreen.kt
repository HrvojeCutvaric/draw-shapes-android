package co.hrvoje.drawshapesandroid.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import co.hrvoje.drawshapesandroid.ui.DrawShapeTheme
import co.hrvoje.drawshapesandroid.utils.Circle
import co.hrvoje.drawshapesandroid.utils.Square
import co.hrvoje.drawshapesandroid.utils.Triangle
import co.hrvoje.drawshapesandroid.view.components.BottomBar
import co.hrvoje.drawshapesandroid.view.components.TapCanvas
import co.hrvoje.drawshapesandroid.view.components.UndoRedoTapBar
import co.hrvoje.drawshapesandroid.viewmodel.MainAction
import co.hrvoje.drawshapesandroid.viewmodel.MainState
import co.hrvoje.drawshapesandroid.viewmodel.MainViewModel

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
            UndoRedoTapBar(
                undoActions = state.undoActions,
                trashUndoActions = state.trashUndoActions,
                onUndoClicked = { onAction(MainAction.OnUndoClicked) },
                onRedoClicked = { onAction(MainAction.OnRedoClicked) },
            )
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
