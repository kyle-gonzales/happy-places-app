package com.example.happyplacesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happyplacesapp.HappyPlaceApp
import com.example.happyplacesapp.adapters.HappyPlaceAdapter
import com.example.happyplacesapp.databinding.ActivityMainBinding
import com.example.happyplacesapp.happy_place_database.HappyPlaceDAO
import com.example.happyplacesapp.happy_place_database.HappyPlaceEntity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private var happyPlaceAdapter : HappyPlaceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val happyPlaceDao = (application as HappyPlaceApp).db.getHappyPlaceDao()

        binding?.fabAdd?.setOnClickListener {
            val addIntent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(addIntent)
        }

        displayAllHappyPlaces(happyPlaceDao)
    }

    private fun displayAllHappyPlaces(happyPlaceDAO: HappyPlaceDAO) {

        lifecycleScope.launch {
            happyPlaceDAO.getAllHappyPlaces().collect { happyPlacesList ->
                setUpRv(ArrayList(happyPlacesList))
            }
        }
    }

    private fun setUpRv(happyPlaces: ArrayList<HappyPlaceEntity>) {
        if (happyPlaces.isEmpty()) {
            binding?.tvEmptyList?.visibility = View.VISIBLE
            binding?.rvHappyPlace?.visibility = View.GONE
        }
        happyPlaceAdapter = HappyPlaceAdapter(happyPlaces)
        binding?.rvHappyPlace?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.rvHappyPlace?.adapter = happyPlaceAdapter

        binding?.tvEmptyList?.visibility = View.GONE
        binding?.rvHappyPlace?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}