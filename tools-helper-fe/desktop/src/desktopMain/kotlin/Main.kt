import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import composeUI.MainScreen
import composeUI.WorkerScreen

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "Simple Worker Screen", state = rememberWindowState()) {
            MainScreen()
        }
    }
}