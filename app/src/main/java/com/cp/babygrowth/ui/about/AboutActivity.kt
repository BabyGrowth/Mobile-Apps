package com.cp.babygrowth.ui.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityEditProfileBinding
import com.cp.babygrowth.ui.main.MainActivity

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("auToLoad", R.id.profile) // Sinyal untuk fragment yang ingin ditampilkan
            startActivity(intent)
            finish()
        }
    }
}