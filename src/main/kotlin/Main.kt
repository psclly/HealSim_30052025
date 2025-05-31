import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    Window(
            onCloseRequest = ::exitApplication,
            title = "LB Simulator 30052025",
            state = WindowState(width = 1200.dp, height = 800.dp)
    ) {
        UIContent()
    }
}



