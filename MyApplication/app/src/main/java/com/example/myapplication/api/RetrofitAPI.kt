package com.example.myapplication.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    private var instance : Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    fun getInstnace() : Retrofit {
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}