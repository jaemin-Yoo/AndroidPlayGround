package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.MovieListAdapter
import com.example.myapplication.databinding.ActivityMovieBinding
import com.example.myapplication.viewmodels.MovieViewModel
import com.example.myapplication.viewmodels.SecondViewModel
import kotlinx.android.synthetic.main.activity_movie.*

/**
 *  ViewModel + LiveData + DataBinding + Repository + Retrofit2 +RecyclerView (https://bb-library.tistory.com/93?category=843046)
 */

class MovieActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMovieBinding>(this, R.layout.activity_movie)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val mAdapter = MovieListAdapter(this)
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}