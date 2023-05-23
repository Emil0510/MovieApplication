package com.emilabdurahmanli.movieapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.emilabdurahmanli.movieapplication.Constants
import com.emilabdurahmanli.movieapplication.R
import com.emilabdurahmanli.movieapplication.adapter.Adapter
import com.emilabdurahmanli.movieapplication.databinding.ActivityPopularMoviesBinding
import com.emilabdurahmanli.movieapplication.model.Result
import com.emilabdurahmanli.movieapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPopularMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.popularMoviesRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,false)
        getMovies()
    }

    fun getMovies(){
        val call: Call<Result?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.popularMovies(
            Constants.API_KEY )
        call?.enqueue(object : Callback<Result?> {
            override fun onResponse(call: Call<Result?>?, response: Response<Result?>) {
                val result: Result = response.body() as Result
                binding.popularMoviesRecyclerView.adapter = Adapter(result.results)
            }

            override fun onFailure(call: Call<Result?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}