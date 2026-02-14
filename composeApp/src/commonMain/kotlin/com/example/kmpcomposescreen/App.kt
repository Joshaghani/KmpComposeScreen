package com.example.kmpcomposescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.content.main.MainScreen
import com.example.kmpcomposescreen.theme.ExampleTheme
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme
import com.github.mohammadjoshaghani.composescreen.app.ComposeScreen
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable

@Composable
fun App() {
    ExampleTheme {
        ComposeScreen(
            listOf(MainScreen()),
            color = it,

            errorScreen = { message, retryClick ->
                Text(
                    message,
                    modifier = Modifier.themeClickable { retryClick() })
            }
        )
    }
}