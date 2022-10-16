package com.example.ehuaproject.navigator

import androidx.navigation.NavHostController
import com.example.ehuaproject.BaseNavigator

class EhuaAppNavigator(navController : NavHostController) : BaseNavigator(navController) {
    fun gotoWelcome() {
        navController.navigate(NavigationRoutes.Welcome.route)
    }

    fun gotoLogin() {
        navController.navigate(NavigationRoutes.OnBoarding.route)
    }
}