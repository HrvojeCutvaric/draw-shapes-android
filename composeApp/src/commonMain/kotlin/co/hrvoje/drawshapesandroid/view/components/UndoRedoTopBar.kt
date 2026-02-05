package co.hrvoje.drawshapesandroid.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.hrvoje.drawshapesandroid.utils.DrawShapeShape
import co.hrvoje.drawshapesandroid.utils.UndoAction
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.ic_redo
import drawshapesandroid.composeapp.generated.resources.ic_undo
import drawshapesandroid.composeapp.generated.resources.redo
import drawshapesandroid.composeapp.generated.resources.selected_shape
import drawshapesandroid.composeapp.generated.resources.tap_to_set_center_point
import drawshapesandroid.composeapp.generated.resources.undo
import org.jetbrains.compose.resources.stringResource

@Composable
fun UndoRedoTapBar(
    selectedShape: DrawShapeShape,
    lastTap: Offset?,
    undoActions: List<UndoAction>,
    trashUndoActions: List<UndoAction>,
    onUndoClicked: () -> Unit,
    onRedoClicked: () -> Unit,
) {
    val isUndoEnabled = remember(undoActions) { undoActions.isNotEmpty() }
    val isRedoVisible =
        remember(trashUndoActions) { trashUndoActions.isNotEmpty() }

    val selectedShapeTitle =
        stringResource(Res.string.selected_shape, stringResource(selectedShape.name))
    val instructionText =
        if (lastTap == null) stringResource(Res.string.tap_to_set_center_point)
        else stringResource(selectedShape.info)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = selectedShapeTitle,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = instructionText,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ChipButton(
            isEnabled = isUndoEnabled,
            label = stringResource(Res.string.undo),
            iconRes = Res.drawable.ic_undo,
            borderColor = if (isUndoEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline,
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = if (isUndoEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline,
            onButtonClicked = onUndoClicked,
        )

        if (isRedoVisible) {
            Spacer(modifier = Modifier.width(16.dp))

            ChipButton(
                label = stringResource(Res.string.redo),
                iconRes = Res.drawable.ic_redo,
                borderColor = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onButtonClicked = onRedoClicked
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}
