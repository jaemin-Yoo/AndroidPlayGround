package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class UpComingMovie (
    @SerializedName("page") val page: String,
    @SerializedName("results") val movieList: List<Movie>
)