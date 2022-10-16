package com.example.ehuaproject.ui.screens.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehuaproject.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel() {
    val uiState  = MutableStateFlow(OnBoardingState())

    fun onLoginEmailChange(email:String){
        uiState.update { it.copy(loginEmail = email) }
    }
    fun onLoginPasswordChange(password:String){
        uiState.update { it.copy(loginPassword = password) }
    }
    fun onScreenModeChange(screenMode: ScreenMode){
        uiState.update { it.copy(screenMode = screenMode) }
    }
    fun onSignUpEmailChange(email:String){
        uiState.update { it.copy(signUpEmail = email) }
    }
    fun onSignUpPasswordChange(password:String){
        uiState.update { it.copy(signUpPassword = password) }
    }
    fun onSignUpFullNameChange(fullName:String){
        uiState.update { it.copy(signUpFullName = fullName) }
    }
    fun onSignUpPhoneNumberChange(phoneNumber:String){
        uiState.update { it.copy(signUpPhoneNumber = phoneNumber) }
    }
    private fun onSignUpSuccess(){
        uiState.update { it.copy(onSignUpSuccess = true, loading = false, success = true) }
    }
    private fun onLoginSuccess() {
        uiState.update { it.copy(onLoginSuccess = true, loading = false, ) }
    }
    private fun onSignUpError(error:String){
        uiState.update { it.copy(onSignUpError = error, loading = false) }
    }
    private fun onLoginError(error:String){
        uiState.update { it.copy(onLoginError = error, loading = false ) }
    }

    fun signUp(userEmail: String, password: String) {
        uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            userRepository.signUpNewUser(userEmail,password).addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    saveUserToFireStore()
                    onSignUpSuccess()
                } else {
                    onSignUpError(task.exception?.message?: "Unknown error")
                }
            }
        }
    }
fun login(userEmail: String, password: String) {
        viewModelScope.launch {
            userRepository.loginUser(userEmail,password).addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    onLoginSuccess()
                } else {
                    onLoginError(task.exception?.message?: "Unknown error")
                }
            }
        }
    }

    private fun saveUserToFireStore() {
        viewModelScope.launch {
            userRepository.saveUserToFireStore(
                uiState.value.signUpEmail,
                uiState.value.signUpPhoneNumber,
                uiState.value.signUpFullName
            ).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Log.d("USER123", "saveUserToFireStore: UserSaved")
                } else {
                    Log.d("USER123", "saveUserToFireStore: ${task.exception?.printStackTrace()}")
                }
            }
        }
    }



}