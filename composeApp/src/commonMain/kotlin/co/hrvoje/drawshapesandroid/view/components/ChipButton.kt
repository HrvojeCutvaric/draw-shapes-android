package co.hrvoje.drawshapesandroid.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChipButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    label: String,
    iconRes: DrawableResource,
    borderColor: Color,
    backgroundColor: Color,
    contentColor: Color,
    onButtonClicked: () -> Unit,
) {
    Surface(
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor,
        ),
        color = backgroundColor,
        contentColor = contentColor,
        shape = CircleShape,
        enabled = isEnabled,
        onClick = onButtonClicked,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(iconRes),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
