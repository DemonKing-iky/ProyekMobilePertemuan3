package com.example.layoutmaterialform

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.layoutmaterialform.ui.screen.FormScreen
import com.example.layoutmaterialform.ui.theme.LayoutMaterialFormTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutMaterialFormTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormScreen()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    LayoutMaterialFormTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Main Activity Light Mode")
@Composable
fun MainActivityPreview() {
    MainContent()
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Main Activity Dark Mode")
@Composable
fun MainActivityDarkPreview() {
    MainContent()
}