package com.agilbudiprasetyo.medicineslistapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.agilbudiprasetyo.medicineslistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navBottom = binding.bnvMain
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fv_main) as NavHostFragment
        val navController = navHostFragment.navController
        navBottom.setupWithNavController(navController)

        navBottom.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_home -> {
                    if (!item.isChecked) {
                        navController.navigate(R.id.action_aboutFragment_to_medicinesFragment)
                    }else{
                        Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.menu_about -> {
                    if (!item.isChecked) {
                        navController.navigate(R.id.action_medicinesFragment_to_aboutFragment)
                    }else{
                        Toast.makeText(this, "about Page", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else ->{
                    false
                }
            }
        }

    }


}