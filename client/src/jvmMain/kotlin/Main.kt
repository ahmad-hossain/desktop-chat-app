import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.ChatScreen
import components.HomeScreen

enum class Screen {
    HOME, CHAT
}

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var userName = remember { "" }

    MaterialTheme {
        when (currentScreen) {
            Screen.HOME -> HomeScreen { userName = it; currentScreen = Screen.CHAT }
            Screen.CHAT -> ChatScreen(userName)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
