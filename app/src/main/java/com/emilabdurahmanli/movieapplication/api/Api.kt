package com.emilabdurahmanli.movieapplication.api

import com.emilabdurahmanli.movieapplication.model.GenreResult
import com.emilabdurahmanli.movieapplication.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("search/movie")
    fun searchMovies(@Query("api_key") apiKey : String, @Query("query")query : String): Call<Result?>?

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey : String): Call<GenreResult?>?

    @GET("movie/upcoming")
    fun upcommingMovies(@Query("api_key") apiKey : String): Call<Result?>?

    @GET("movie/popular")
    fun popularMovies(@Query("api_key") apiKey : String): Call<Result?>?
}