package com.example.kmpcomposescreen

import androidx.compose.runtime.Composable
import com.example.kmpcomposescreen.screen.SecondScreen
import com.example.kmpcomposescreen.theme.ExampleTheme
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme
import com.github.mohammadjoshaghani.composescreen.app.ComposeScreen
import com.github.mohammadjoshaghani.composescreen.utils.Config
import kmpcomposescreen.composeapp.generated.resources.Res
import kmpcomposescreen.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ExampleTheme {
        ComposeScreen(
            SecondScreen(),
            config = Config(
                appIconId = Res.drawable.compose_multiplatform,
                isDarkTheme = false,
                color = getApplicationColorScheme(false),
                errorScreen = { message, retryClick -> }
            )
        )
    }
}