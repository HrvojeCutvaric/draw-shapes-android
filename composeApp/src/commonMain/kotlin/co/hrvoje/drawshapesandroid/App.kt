package co.hrvoje.drawshapesandroid

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.hrvoje.drawshapesandroid.ui.DrawShapeTheme
import co.hrvoje.drawshapesandroid.view.MainScreen

@Composable
@Preview
fun App() {
    DrawShapeTheme {
        MainScreen()
    }
}
