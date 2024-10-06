package com.example.moviemania

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.presentation.ui.HomeScreen

@Composable
fun BaseAppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }

        composable<MovieDetails> {
        }
    }
}