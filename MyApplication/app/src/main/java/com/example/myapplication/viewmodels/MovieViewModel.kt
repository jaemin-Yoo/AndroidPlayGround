package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Movie
import com.example.myapplication.repository.Repository

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))
    private val movies = repository.getMovieData()

    // xml 연결 (listData)
    fun getMovieData(): LiveData<List<Movie>>{
        return movies
    }
}