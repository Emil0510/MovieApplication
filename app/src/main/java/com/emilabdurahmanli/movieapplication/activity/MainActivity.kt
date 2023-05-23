package com.emilabdurahmanli.movieapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emilabdurahmanli.movieapplication.R
import com.emilabdurahmanli.movieapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.searchMoviesText.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        binding.showUpcominMoviesText.setOnClickListener {
            val intent = Intent(this,UpcomingMoviesActivity::class.java)
            startActivity(intent)
        }

        binding.showPopularMoviesText.setOnClickListener {
            val intent = Intent(this,PopularMoviesActivity::class.java)
            startActivity(intent)
        }
    }
}