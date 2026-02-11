package com.example.kmpcomposescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.MainScreen
import com.example.kmpcomposescreen.screen.SettingScreen
import com.example.kmpcomposescreen.theme.ExampleTheme
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme
import com.github.mohammadjoshaghani.composescreen.app.ComposeScreen
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable
import com.github.mohammadjoshaghani.composescreen.utils.Config
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {
    ExampleTheme {

        startKoin {

        }
        ComposeScreen(
            listOf(MainScreen() , SettingScreen()),
            config = Config(
                isDarkTheme = true,
                isRtl = false,
                color = getApplicationColorScheme(true),
            ),
            loadingScreen = {
                Column {
                    Text("Loading...")
                    CircularProgressIndicator(strokeWidth = 1.dp)
                }
            },
            errorScreen = { message, retryClick ->
                Text(
                    message,
                    modifier = Modifier.themeClickable { retryClick() })
            }
        )
    }
}