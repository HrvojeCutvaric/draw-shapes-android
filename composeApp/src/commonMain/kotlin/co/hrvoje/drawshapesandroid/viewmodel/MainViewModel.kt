package co.hrvoje.drawshapesandroid.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import co.hrvoje.drawshapesandroid.utils.Circle
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape
import co.hrvoje.drawshapesandroid.utils.Square
import co.hrvoje.drawshapesandroid.utils.Triangle
import co.hrvoje.drawshapesandroid.utils.UndoAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val shapes = listOf(Circle(), Square(), Triangle())

    private val _state = MutableStateFlow(
        MainState(
            shapes = shapes,
            selectedShape = shapes.first(),
            lastTap = null,
            drawnShapes = emptyList(),
            undoActions = emptyList(),
            trashUndoActions = emptyList(),
        )
    )
    val state = _state.asStateFlow()

    fun execute(action: MainAction) {
        when (action) {
            is MainAction.OnShapeSelected -> onSelectShape(action.shape)
            is MainAction.OnTap -> onTap(action.offset)
            MainAction.OnUndoClicked -> onUndoClicked()
            MainAction.OnRedoClicked -> onRedoClicked()
        }
    }

    private fun onRedoClicked() {
        val state = _state.value
        val trashUndoActions = state.trashUndoActions.toMutableList()
        val undoActions = state.undoActions.toMutableList()

        val last = trashUndoActions.removeLastOrNull() ?: return

        undoActions.add(last)
        _state.update {
            it.copy(
                undoActions = undoActions,
                trashUndoActions = trashUndoActions
            )
        }

        when (last) {
            is UndoAction.Tap -> {
                _state.update { it.copy(lastTap = last.offset) }
            }

            is UndoAction.Shape -> {
                _state.update { it.copy(drawnShapes = it.drawnShapes + last.shape, lastTap = null) }
            }
        }
    }

    private fun onUndoClicked() {
        val state = _state.value
        val trashUndoActions = state.trashUndoActions.toMutableList()
        val undoActions = state.undoActions.toMutableList()

        val last = undoActions.removeLastOrNull() ?: return

        trashUndoActions.add(last)
        _state.update {
            it.copy(
                undoActions = undoActions,
                trashUndoActions = trashUndoActions
            )
        }

        when (last) {
            is UndoAction.Tap -> {
                _state.update { it.copy(lastTap = null) }
            }

            is UndoAction.Shape -> {
                val lastTap = undoActions.last() as UndoAction.Tap
                _state.update {
                    it.copy(
                        drawnShapes = it.drawnShapes - last.shape,
                        lastTap = lastTap.offset
                    )
                }
            }
        }
    }

    private fun onTap(tapPosition: Offset) {
        val state = _state.value
        val trashUndoActions = state.trashUndoActions.toMutableList()
        val undoActions = state.undoActions.toMutableList()

        trashUndoActions.clear()

        if (state.lastTap == null) {
            _state.update {
                it.copy(
                    lastTap = tapPosition,
                    undoActions = undoActions,
                    trashUndoActions = trashUndoActions,
                )
            }
            undoActions.add(UndoAction.Tap(tapPosition))
            return
        }

        val newShape = state.selectedShape.createWithPoints(
            centerPoint = state.lastTap,
            referencePoint = tapPosition,
        )
        undoActions.add(UndoAction.Shape(newShape))

        _state.update {
            it.copy(
                drawnShapes = state.drawnShapes + newShape,
                lastTap = null,
                undoActions = undoActions,
                trashUndoActions = trashUndoActions,
            )
        }
    }

    private fun onSelectShape(shape: DrawShapeShape) {
        _state.update { it.copy(selectedShape = shape) }
    }
}
