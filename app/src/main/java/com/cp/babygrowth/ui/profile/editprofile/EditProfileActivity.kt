package com.cp.babygrowth.ui.profile.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    private fun showMenu() {
        val menuEditPhoto = PopupMenu(this, findViewById(R.id.menu_edit_image))
        menuEditPhoto.menuInflater.inflate(R.menu.menu_edit_photo, menuEditPhoto.menu)

        menuEditPhoto.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.camera -> {
                    true
                }
                R.id.gallery -> {
                    true
                }
                else -> false
            }
        }

        menuEditPhoto.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuEditImage.setOnClickListener {
            showMenu()
        }

    }
}