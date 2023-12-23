package com.cp.babygrowth.ui.profile.profileanak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityProfileAnakBinding
import com.cp.babygrowth.ui.main.MainActivity
import com.cp.babygrowth.ui.profile.profileanak.addprofileanak.AddProfileAnakActivity
import androidx.recyclerview.widget.LinearLayoutManager

class ProfileAnakActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAnakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBtn()
    }

    private fun actionBtn() {
        binding.fabAdd.setOnClickListener {
            val addBtn = Intent(this, AddProfileAnakActivity::class.java)
            startActivity(addBtn)
        }

        binding.backHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("fragmentToLoad", R.id.profile) // Sinyal untuk fragment yang ingin ditampilkan
            startActivity(intent)
            finish()
        }
    }
}