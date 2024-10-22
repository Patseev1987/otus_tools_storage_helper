import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "Simple Worker Screen", state = rememberWindowState()) {
            WorkerScreen(){
                println(it)
            }
        }
    }
}