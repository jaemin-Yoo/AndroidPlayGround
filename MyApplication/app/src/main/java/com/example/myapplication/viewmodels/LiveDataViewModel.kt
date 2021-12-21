package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel(){
    private lateinit var data: MutableLiveData<Int>

    fun getData(): LiveData<Int>{
        if (data == null){
            data = MutableLiveData(0)
        }

        return data
    }

    private fun incData(){
        data.value = data.value!! + 1
        Log.i("LiveDataViewModel", data.value.toString())
    }
}