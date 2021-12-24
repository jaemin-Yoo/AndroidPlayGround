package com.example.myapplication.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.UpComingMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") api_key: String = BuildConfig.MOVIE_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<UpComingMovie>
}