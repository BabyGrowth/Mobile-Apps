package com.cp.babygrowth.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cp.babygrowth.R
import com.cp.babygrowth.ui.authentication.FirebaseAuthentication
import com.cp.babygrowth.ui.authentication.signin.SigninActivity
import com.cp.babygrowth.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var authManager: FirebaseAuthentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        authManager = FirebaseAuthentication.getInstance(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = authManager.getCurrentUser()
            val intent = if (currentUser != null) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, SigninActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}