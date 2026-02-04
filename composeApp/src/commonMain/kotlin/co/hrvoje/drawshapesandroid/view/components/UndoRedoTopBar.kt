package co.hrvoje.drawshapesandroid.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.hrvoje.drawshapesandroid.utils.UndoAction
import drawshapesandroid.composeapp.generated.resources.Res
import drawshapesandroid.composeapp.generated.resources.ic_redo
import drawshapesandroid.composeapp.generated.resources.ic_undo
import drawshapesandroid.composeapp.generated.resources.redo
import drawshapesandroid.composeapp.generated.resources.undo
import org.jetbrains.compose.resources.stringResource

@Composable
fun UndoRedoTapBar(
    undoActions: List<UndoAction>,
    trashUndoActions: List<UndoAction>,
    onUndoClicked: () -> Unit,
    onRedoClicked: () -> Unit,
) {
    val isUndoEnabled = remember(undoActions) { undoActions.isNotEmpty() }
    val isRedoVisible =
        remember(trashUndoActions) { trashUndoActions.isNotEmpty() }

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
            onButtonClicked = onUndoClicked,
        )

        if (isRedoVisible) {
            Spacer(modifier = Modifier.width(16.dp))

            ChipButton(
                label = stringResource(Res.string.redo),
                iconRes = Res.drawable.ic_redo,
                borderColor = MaterialTheme.colorScheme.outline,
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                onButtonClicked = onRedoClicked
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}
