package com.example.ehuaproject.ui.screens.onboarding

data class OnBoardingState(
    val loginEmail: String = "",
    val loginPassword: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val signUpFullName: String = "",
    val signUpPhoneNumber: String = "",
    val screenMode: ScreenMode = ScreenMode.Login,
    val onSignUpSuccess: Boolean = false,
    val onLoginSuccess: Boolean = false,
    val onSignUpError: String = "",
    val onLoginError: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,

    )
