package com.example.myapplication.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R

object ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String){
        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        Glide.with(imageView.context).load(baseUrl+url).error(R.drawable.ic_launcher_background).into(imageView)
    }
}