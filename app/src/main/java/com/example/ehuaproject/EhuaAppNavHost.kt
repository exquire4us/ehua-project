package com.example.ehuaproject

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ehuaproject.navigator.EhuaAppNavigator
import com.example.ehuaproject.navigator.NavigationRoutes
import com.example.ehuaproject.ui.screens.onboarding.OnBoardingScreen
import com.example.ehuaproject.ui.screens.onboarding.OnBoardingViewModel
import com.example.ehuaproject.ui.screens.onboarding.WelcomeScreen

@Composable
internal fun EhuaAppNavHost() {
    val navController = rememberNavController()
    val navigator = EhuaAppNavigator(navController)
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Welcome.route
    ) {
        composable(route = NavigationRoutes.Welcome.route){
            WelcomeScreen(navigator = navigator)
        }
        composable(route = NavigationRoutes.OnBoarding.route){
            val viewModel : OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(navigator = navigator, viewModel = viewModel)
        }
    }
}
