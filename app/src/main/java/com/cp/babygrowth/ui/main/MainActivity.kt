package com.cp.babygrowth.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

                else ->{

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_activity,fragment)
        fragmentTransaction.commit()
    }
}