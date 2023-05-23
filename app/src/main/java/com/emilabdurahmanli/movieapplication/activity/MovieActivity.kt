package com.emilabdurahmanli.movieapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emilabdurahmanli.movieapplication.Constants
import com.emilabdurahmanli.movieapplication.adapter.Adapter
import com.emilabdurahmanli.movieapplication.databinding.ActivityMovieBinding
import com.emilabdurahmanli.movieapplication.model.Genre
import com.emilabdurahmanli.movieapplication.model.GenreResult
import com.emilabdurahmanli.movieapplication.model.Movie
import com.emilabdurahmanli.movieapplication.model.Result
import com.emilabdurahmanli.movieapplication.network.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieBinding
    private lateinit var movie : Movie
    private  var genre : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = getIntent()
        movie = intent.getSerializableExtra("Movie") as Movie
        getGenre()
    }

    fun getGenre(){
        val call: Call<GenreResult?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getGenres(Constants.API_KEY)
        call?.enqueue(object : Callback<GenreResult?> {
            override fun onResponse(call: Call<GenreResult?>?, response: Response<GenreResult?>) {
                val result: GenreResult = response.body() as GenreResult
                movie.genre_ids?.forEach {
                    for (i in 0..result.genres.size - 1){
                        if (it == result.genres[i].id){
                            genre = genre +" ${result.genres[i].name}"
                        }
                    }
                }
                operate()
            }

            override fun onFailure(call: Call<GenreResult?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
    fun operate(){
        Picasso.get().load(Constants.imageUrl+movie.poster_path).into(binding.movieImage)
        binding.movieTitleText.setText(movie.title)
        binding.overviewText.setText("Overview: ${movie.overview}")
        binding.languageText.setText("Language: ${movie.original_language}")
        binding.popularityText.setText("Popularity: ${movie.popularity.toString()}")
        binding.genreText.setText("Genres: $genre")
    }
}