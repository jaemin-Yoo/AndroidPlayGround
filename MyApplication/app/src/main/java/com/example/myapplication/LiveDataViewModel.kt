package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel(){
    private val user: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            loadUser()
        }
    }

    fun getUser(): LiveData<String>{
        return user
    }

    private fun loadUser(){

    }
}