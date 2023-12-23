package com.cp.babygrowth.ui.authentication.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cp.babygrowth.databinding.ActivitySigninBinding
import com.cp.babygrowth.ui.authentication.FirebaseAuthentication
import com.cp.babygrowth.ui.main.MainActivity
import android.widget.Toast
import com.cp.babygrowth.ui.authentication.signup.SignupActivity

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var authManager: FirebaseAuthentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        authManager = FirebaseAuthentication(this)

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
        }
    }