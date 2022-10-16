package com.example.ehuaproject.ui.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ehuaproject.navigator.EhuaAppNavigator
import com.example.ehuaproject.ui.components.LoadingProgressIndicator
import com.example.ehuaproject.ui.screens.login.LoginScreen
import com.example.ehuaproject.ui.screens.signUp.SignUpScreen

@Composable
fun OnBoardingScreen(
    navigator: EhuaAppNavigator,
    viewModel: OnBoardingViewModel
) {
    val onBoardingState by viewModel.uiState.collectAsState()
    val showLoader by derivedStateOf { onBoardingState.loading }
    Box {

        AnimatedVisibility(
            visible = onBoardingState.screenMode == ScreenMode.Login,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoginScreen(
                userName = onBoardingState.loginEmail,
                password = onBoardingState.loginPassword,
                onLogin = {
                    navigator.gotoWelcome()
                },
                onPasswordChange = { viewModel.onLoginPasswordChange(it) },
                onUserNameChange = { viewModel.onLoginEmailChange(it) },
                onRegister = { viewModel.onScreenModeChange(ScreenMode.SignUp) }
            )
        }
        AnimatedVisibility(
            visible = onBoardingState.screenMode == ScreenMode.SignUp,
            enter = fadeIn(animationSpec = tween(1000, easing = FastOutLinearInEasing)),
            exit = fadeOut()
        ) {
            SignUpScreen(
                email = onBoardingState.signUpEmail,
                password = onBoardingState.signUpPassword,
                onSignUp = {
                    viewModel.signUp(onBoardingState.signUpEmail, onBoardingState.signUpPassword)
                },
                phoneNumber = onBoardingState.signUpPhoneNumber,
                fullName = onBoardingState.signUpFullName,
                onEmailChange = {viewModel.onSignUpEmailChange(it)},
                onPasswordChange = {viewModel.onSignUpPasswordChange(it)},
                onPhoneNumberChange = {viewModel.onSignUpPhoneNumberChange(it)},
                onFullNameChange = {viewModel.onSignUpFullNameChange(it)},
                showSignUpMessage = onBoardingState.onSignUpSuccess,
            )
        }
        if (showLoader) {
            LoadingProgressIndicator(modifier = Modifier)
        }
    }


}

