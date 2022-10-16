package com.example.ehuaproject.navigator

sealed class NavigationRoutes(val route: String){
    object Welcome: NavigationRoutes("welcome")
    object OnBoarding: NavigationRoutes("onBoarding")
    object Register: NavigationRoutes("register")
}