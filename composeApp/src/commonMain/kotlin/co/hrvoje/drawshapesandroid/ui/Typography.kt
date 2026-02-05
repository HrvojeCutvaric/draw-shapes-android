package co.hrvoje.drawshapesandroid.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun typography(): Typography = Typography(
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineMedium = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold
    ),
)
