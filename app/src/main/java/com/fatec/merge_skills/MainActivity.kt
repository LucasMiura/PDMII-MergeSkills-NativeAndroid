package com.fatec.merge_skills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fatec.merge_skills.ui.screens.aula03.Aula03Screen
import com.fatec.merge_skills.ui.theme.MergeskillskotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MergeskillskotlinTheme {
                Aula03Screen()
            }
        }
    }
}