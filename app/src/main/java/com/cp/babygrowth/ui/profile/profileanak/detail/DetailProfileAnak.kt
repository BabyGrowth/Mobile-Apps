package com.cp.babygrowth.ui.profile.profileanak.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityDetailProfileAnakBinding
import com.cp.babygrowth.databinding.ActivityEditProfileBinding

class DetailProfileAnak : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProfileAnakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra(EXTRA_NAME)
        val place = intent.getStringExtra(EXTRA_TEMPAT)
        val date = intent.getStringExtra(EXTRA_TANGGAL)
        val gender = intent.getStringExtra(EXTRA_GENDER)
        val weight = intent.getStringExtra(EXTRA_BERAT)
        val height = intent.getStringExtra(EXTRA_TINGGI)

        binding.namaDetail.text = nama
        binding.tempatDetail.text = place
        binding.tanggalDetail.text = date
        binding.genderDetail.text = gender
        binding.beratDetail.text = weight
        binding.tinggiDetail.text = height
    }

    companion object {
        const val EXTRA_NAME = "extra_title"
        const val EXTRA_TEMPAT = "extra_tempat"
        const val EXTRA_TANGGAL = "extra_tanggal"
        const val EXTRA_GENDER = "extra_gender"
        const val EXTRA_BERAT = "extra_berat"
        const val EXTRA_TINGGI = "extra_tinggi"
    }
}