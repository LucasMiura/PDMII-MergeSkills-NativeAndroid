package com.fatec.merge_skills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fatec.merge_skills.ui.screens.auth.LoginScreen
import com.fatec.merge_skills.ui.screens.auth.RegisterScreen
import com.fatec.merge_skills.ui.screens.auth.SplashScreen
import com.fatec.merge_skills.ui.screens.aula07.Aula07Screen
import com.fatec.merge_skills.ui.screens.aula07.Aula07ViewModel
import com.fatec.merge_skills.ui.theme.MergeskillskotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MergeskillskotlinTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen(
                            onSplashFinished = {
                                navController.navigate("login") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("login") {
                        val authViewModel = androidx.lifecycle.viewmodel.compose.viewModel<com.fatec.merge_skills.ui.screens.auth.AuthViewModel>()
                        LoginScreen(
                            viewModel = authViewModel,
                            onNavigateToRegister = { navController.navigate("register") },
                            onLoginSuccess = { navController.navigate("aula07") }
                        )
                    }
                    composable("register") {
                        val authViewModel = androidx.lifecycle.viewmodel.compose.viewModel<com.fatec.merge_skills.ui.screens.auth.AuthViewModel>()
                        RegisterScreen(
                            viewModel = authViewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                    composable("aula07") {
                        // Instanciando ViewModel manualmente por enquanto.
                        // Na Aula 09 usaremos o Koin ( koinViewModel() )
                        val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<Aula07ViewModel>()
                        Aula07Screen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}