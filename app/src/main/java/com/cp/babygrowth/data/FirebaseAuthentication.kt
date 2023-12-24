package com.cp.babygrowth.data

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
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

    fun signInWithGoogle(account: GoogleSignInAccount?, onComplete: (Boolean, String) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Sign in with Google successful")
                } else {
                    onComplete(false, task.exception?.message ?: "Sign in with Google failed")
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

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