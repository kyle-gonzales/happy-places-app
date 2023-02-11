package com.example.happyplacesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happyplacesapp.activities.AddHappyPlaceActivity
import com.example.happyplacesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.fabAdd?.setOnClickListener {
            val addIntent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(addIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}