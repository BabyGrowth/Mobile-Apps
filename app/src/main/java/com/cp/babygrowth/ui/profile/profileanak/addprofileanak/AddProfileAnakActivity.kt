package com.cp.babygrowth.ui.profile.profileanak.addprofileanak

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityAddProfileAnakBinding
import com.cp.babygrowth.ui.profile.profileanak.ProfileAnakActivity
import com.google.android.material.textfield.TextInputEditText

class AddProfileAnakActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProfileAnakBinding
    private lateinit var dateOfBirthTextView: TextView
    private lateinit var genderRadioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateOfBirthTextView = findViewById(R.id.date_birth_tv)
        val pickDateButton: ImageButton = findViewById(R.id.date_birth_button)
        pickDateButton.setOnClickListener {
            showDatePicker()
        }

        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        genderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonMale -> {

                }
                R.id.radioButtonFemale -> {

                }
            }
        }

        actionBtn()
    }

    private fun actionBtn() {
        binding.backHome.setOnClickListener {
            val back = Intent(this, ProfileAnakActivity::class.java)
            startActivity(back)
            finish()
        }

        binding.addBtn.setOnClickListener {
            saveProfile()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateOfBirthTextView.text = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun saveProfile() {
        val nameEditText: TextInputEditText = findViewById(R.id.nama_anakEditText)
        val placeEditText: TextInputEditText = findViewById(R.id.place_anakEditText)
        val weightEditText: TextInputEditText = findViewById(R.id.berat_anakEditText)
        val heightEditText: TextInputEditText = findViewById(R.id.tinggi_anakEditText)

        val name = nameEditText.text.toString()
        val place = placeEditText.text.toString()
        val weight = weightEditText.text.toString()
        val height = heightEditText.text.toString()

        val gender = when (genderRadioGroup.checkedRadioButtonId) {
            R.id.radioButtonMale -> "Male"
            R.id.radioButtonFemale -> "Female"
            else -> ""
        }

        val dateOfBirth = dateOfBirthTextView.text.toString()
    }
}