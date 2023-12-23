package com.cp.babygrowth.ui.authentication

import android.app.Activity
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class FirebaseAuthentication(private val context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpWithEmailAndPassword(name: String, email: String, password: String, onComplete: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileUpdateTask ->
                            if (profileUpdateTask.isSuccessful) {
                                onComplete(true, "Sign up successful")
                            } else {
                                onComplete(false, profileUpdateTask.exception?.message ?: "Failed to update profile")
                            }
                        }
                } else {
                    onComplete(false, task.exception?.message ?: "Sign up failed")
                }
            }
    }

    fun signInWithEmailAndPassword(email: String, password: String, onComplete: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Sign in successful")
                } else {
                    onComplete(false, task.exception?.message ?: "Sign in failed")
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

    // Mendapatkan informasi pengguna yang sedang login
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    companion object {
        @JvmStatic
        fun getInstance(context: Context): FirebaseAuthentication {
            return FirebaseAuthentication(context)
        }
    }
}