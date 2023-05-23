package com.emilabdurahmanli.movieapplication.activity

import android.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.emilabdurahmanli.movieapplication.Constants
import com.emilabdurahmanli.movieapplication.adapter.Adapter
import com.emilabdurahmanli.movieapplication.databinding.ActivitySearchBinding
import com.emilabdurahmanli.movieapplication.model.Result
import com.emilabdurahmanli.movieapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.moviesSearchRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,false)
        binding.searchButton.setOnClickListener {
            getMovies(binding.searchEditText.text.toString())
        }
    }

    fun getMovies(query : String){
        val call: Call<Result?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.searchMovies(Constants.API_KEY,query )
        call?.enqueue(object : Callback<Result?> {
            override fun onResponse(call: Call<Result?>?, response: Response<Result?>) {
                val result: Result = response.body() as Result
                binding.moviesSearchRecyclerView.adapter = Adapter(result.results)
            }

            override fun onFailure(call: Call<Result?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }


}