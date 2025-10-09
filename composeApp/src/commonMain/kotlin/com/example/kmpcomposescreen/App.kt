package com.example.kmpcomposescreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kmpcomposescreen.screen.main.MainScreen
import com.example.kmpcomposescreen.theme.ExampleTheme
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme
import com.github.mohammadjoshaghani.composescreen.app.ComposeScreen
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable
import com.github.mohammadjoshaghani.composescreen.utils.Config
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ExampleTheme {
        ComposeScreen(
            MainScreen(),
            config = Config(
                isDarkTheme = false,
                color = getApplicationColorScheme(false),
                errorScreen = { message, retryClick ->
                    Text(
                        message,
                        modifier = Modifier.themeClickable { retryClick() })
                }
            )
        )
    }
}