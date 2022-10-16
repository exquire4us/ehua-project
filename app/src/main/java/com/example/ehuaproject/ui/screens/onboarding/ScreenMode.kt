package com.example.ehuaproject.ui.screens.onboarding

sealed interface ScreenMode {
    object Login: ScreenMode
    object SignUp: ScreenMode
}
