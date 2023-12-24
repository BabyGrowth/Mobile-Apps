package com.cp.babygrowth.ui.authentication.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cp.babygrowth.databinding.ActivitySignupBinding
import com.cp.babygrowth.data.FirebaseAuthentication
import com.cp.babygrowth.ui.authentication.signin.SigninActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var authManager: FirebaseAuthentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authManager = FirebaseAuthentication(this)

        binding.signupBtn.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            binding.progressBar.visibility = View.GONE

            authManager.signUpWithEmailAndPassword(name, email, password) { success, message ->

                if (success) {
                    Toast.makeText(this, "Signup Berhasil, Silahkan Login", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, SigninActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Signup Gagal", Toast.LENGTH_SHORT).show()

                    binding.nameEditText.text = null
                    binding.emailEditText.text = null
                    binding.passwordEditText.text = null
                }
            }
        }

        binding.btnToSignin.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }
}