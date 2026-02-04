package co.hrvoje.drawshapesandroid.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import co.hrvoje.drawshapesandroid.utils.Circle
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape
import co.hrvoje.drawshapesandroid.utils.Square
import co.hrvoje.drawshapesandroid.utils.Triangle
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
        )
    )
    val state = _state.asStateFlow()

    fun execute(action: MainAction) {
        when (action) {
            is MainAction.OnShapeSelected -> onSelectShape(action.shape)
            is MainAction.OnTap -> onTap(action.offset)
        }
    }

    private fun onTap(offset: Offset) {
        val state = _state.value

        if (state.lastTap == null) {
            _state.update { it.copy(lastTap = offset) }
            return
        }

        val newShape = createShape(state = state, lastTap = state.lastTap, offset = offset)

        _state.update {
            it.copy(
                drawnShapes = state.drawnShapes + newShape,
                lastTap = null,
            )
        }
    }

    private fun createShape(
        state: MainState,
        lastTap: Offset,
        offset: Offset,
    ): DrawShapeShape = when (state.selectedShape) {
        is Circle -> Circle(
            first = lastTap,
            second = offset,
        )

        is Square -> Square(
            first = lastTap,
            second = offset,
        )

        is Triangle -> Triangle(
            first = lastTap,
            second = offset,
        )
    }

    private fun onSelectShape(shape: DrawShapeShape) {
        _state.update { it.copy(selectedShape = shape) }
    }
}
