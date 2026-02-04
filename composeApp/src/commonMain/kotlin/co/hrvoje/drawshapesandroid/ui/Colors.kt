package co.hrvoje.drawshapesandroid.ui

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFFE3750A)
val PrimaryVariant = Color(0xFF89591B)
val OnPrimary = Color(0xFFFFFFFF)
val Secondary = Color(0xFFD200B6)
val OnSecondary = Color(0xFFFFFFFF)
val Outline = Color(0xFF9E9E9E)
val OutlineVariant = Color(0x1F000000)
val OnBackground = Color(0xFF000000)
val Surface = Color(0xFFFFFFFF)
val OnSurface = Color(0xFF000000)
val OnSurfaceVariant = Color(0xFF616161)
val Error = Color(0xFFF44336)
val OnError = Color(0xFFFFFFFF)
val Background = Color(0xFFFFFFFF)

internal val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = Background,
    onBackground = OnBackground,
    error = Error,
    onError = OnError,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant,
)
