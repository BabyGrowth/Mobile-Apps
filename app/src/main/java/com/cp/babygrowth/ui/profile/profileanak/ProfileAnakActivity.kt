package com.cp.babygrowth.ui.profile.profileanak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityAddProfileAnakBinding
import com.cp.babygrowth.databinding.ActivityProfileAnakBinding
import com.cp.babygrowth.ui.profile.profileanak.adaprofileanak.AddProfileAnakActivity

class ProfileAnakActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAnakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener{
            val addBtn = Intent(this, AddProfileAnakActivity::class.java)
            startActivity(addBtn)
        }
    }
}