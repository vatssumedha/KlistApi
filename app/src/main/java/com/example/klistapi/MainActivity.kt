package com.example.klistapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.klistapi.ui.theme.KListApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sets the main Composable content
        setContent {
            // Apply custom app theme
            KListApiTheme {
                // Display the demo screen using KList
                KListDemo()
            }
        }
    }
}
