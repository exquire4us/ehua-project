package com.example.ehuaproject.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val fireBaseAuth: FirebaseAuth,
    private val fireBaseFireStore: FirebaseFirestore
)  {
    fun signUpNewUser(email: String, password: String) = fireBaseAuth.createUserWithEmailAndPassword(email, password)
    fun loginUser(email: String, password: String ) = fireBaseAuth.signInWithEmailAndPassword(email, password)
    fun saveUserToFireStore(email: String, phoneNumber: String, fullName: String)  = fireBaseFireStore.collection("users").document(fireBaseAuth.currentUser?.uid?: "").set(
        hashMapOf(
            "email" to email,
            "phoneNumber" to phoneNumber,
            "fullName" to fullName
        )
    )

}