package com.cp.babygrowth.ui.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cp.babygrowth.R
import com.cp.babygrowth.databinding.ActivityMainBinding
import com.cp.babygrowth.ui.history.HistoryFragment
import com.cp.babygrowth.ui.home.HomeFragment
import com.cp.babygrowth.ui.mealplan.MealFragment
import com.cp.babygrowth.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(HomeFragment())
                R.id.history -> replaceFragment(HistoryFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.meal_plan -> replaceFragment(MealFragment())

                R.id.fab_scan -> checkCameraPermission()

                else ->{

                }
            }
            true
        }

        val paToLoad = intent.getIntExtra("paToLoad", -1)
        if (paToLoad ==  R.id.profile) {
            replaceFragment(ProfileFragment())
        }

        val epToLoad = intent.getIntExtra("epToLoad", -1)
        if (epToLoad ==  R.id.profile) {
            replaceFragment(ProfileFragment())
        }

        val auToLoad = intent.getIntExtra("auToLoad", -1)
        if (auToLoad ==  R.id.profile) {
            replaceFragment(ProfileFragment())
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_activity,fragment)
        fragmentTransaction.commit()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            Log.e("MainActivity", "Launching camera intent")
            launcherIntentCamera.launch(null)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            // Handle success, misalnya tampilkan gambar yang diambil dari kamera
        } else {
            // Handle failure atau pemberitahuan jika pengguna membatalkan pengambilan gambar
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("MainActivity", "Camera permission granted")
                launcherIntentCamera.launch(null)
            } else {
                Log.e("MainActivity", "Camera permission denied")
                Toast.makeText(
                    this,
                    "Camera permission is required to use this feature",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }
}