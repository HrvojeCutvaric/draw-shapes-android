package co.hrvoje.drawshapesandroid.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun typography(): Typography = Typography(
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
    ),
)
