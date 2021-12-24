package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("release_date") val release_date: String
)
