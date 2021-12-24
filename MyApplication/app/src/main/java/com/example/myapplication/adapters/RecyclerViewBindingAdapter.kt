package com.example.myapplication.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Movie

object RecyclerViewBindingAdapter {
    @BindingAdapter("listData")
    @JvmStatic
    fun bindData(recyclerView: RecyclerView, movies: List<Movie>?){
        val adapter = recyclerView.adapter as MovieListAdapter
        adapter.submitList(movies)
    }
}