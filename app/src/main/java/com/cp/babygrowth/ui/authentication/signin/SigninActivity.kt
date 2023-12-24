package com.cp.babygrowth.ui.authentication.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cp.babygrowth.databinding.ActivitySigninBinding
import com.cp.babygrowth.data.FirebaseAuthentication
import com.cp.babygrowth.ui.main.MainActivity
import android.widget.Toast
import com.cp.babygrowth.R
import com.cp.babygrowth.ui.authentication.signup.SignupActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var authManager: FirebaseAuthentication

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        authManager = FirebaseAuthentication(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_ID_Google))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signinBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            authManager.signInWithEmailAndPassword(email, password) { success, message ->
                    if (success) {
                        Toast.makeText(this, "Signin Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Signin Gagal, Silahkan cek kembali",
                            Toast.LENGTH_SHORT
                        ).show()

                        binding.emailEditText.text = null
                        binding.passwordEditText.text = null
                    }
                }
            }

            binding.btnToSignup.setOnClickListener {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }

            binding.googleBtn.setOnClickListener {
                signInWithGoogle()
            }
        }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            authManager.signInWithGoogle(account) { success, message ->
                if (success) {
                    Toast.makeText(this, "Sign in with Google berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Sign in with Google gagal", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: ApiException) {
            Toast.makeText(this, "Sign in with Google gagal", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }
}