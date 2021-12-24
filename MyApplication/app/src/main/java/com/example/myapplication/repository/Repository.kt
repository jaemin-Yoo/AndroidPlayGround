package com.example.myapplication.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.MovieService
import com.example.myapplication.api.RetrofitAPI
import com.example.myapplication.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Repository(mDatabase: AppDatabase) {
    private val textDao = mDatabase.textDao()
    private val countDao = mDatabase.countDao()

    private val retrofit: Retrofit = RetrofitAPI.getInstnace()
    private val api = retrofit.create(MovieService::class.java)

    val allText: LiveData<List<TextEntity>> = textDao.getAll()
    val allCount: LiveData<Int> = countDao.getCount()

    companion object{
        private var sInstance: Repository?= null
        fun getInstance(database: AppDatabase): Repository{
            return sInstance
                ?: synchronized(this){
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }

    suspend fun textInsert(textEntity: TextEntity){
        textDao.insert(textEntity)
    }

    suspend fun textDeleteAll(){
        textDao.deleteAll()
    }

    suspend fun countUpdate(countEntity: CountEntity){
        countDao.update(countEntity)
    }

    fun getMovieData(): LiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        api.getUpcomingMovie().enqueue(object : Callback<UpComingMovie> {
            override fun onResponse(call: Call<UpComingMovie>, response: Response<UpComingMovie>) {
                data.value = response.body()!!.movieList
            }
            override fun onFailure(call: Call<UpComingMovie>, t: Throwable) {
                t.stackTrace
            }
        })
        return data
    }
}