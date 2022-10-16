package com.example.ehuaproject

import androidx.navigation.NavHostController

abstract class BaseNavigator (val navController: NavHostController) {
    fun back(): Boolean {
        return navController.popBackStack()
    }
}