package com.example.myapplication.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application): AndroidViewModel(application) {
    val name = ObservableField("")
    var count = MutableLiveData<Int>()

    init{
        name.set("Yoo")
        count.value = 0
    }

    fun nameClick(){
        if (name.get() == "Yoo"){
            name.set("Jaemin")
        } else{
            name.set("Yoo")
        }
    }

    fun increase(){
        count.value = count.value?.plus(1)
    }

    fun decrease(){
        count.value = count.value?.minus(1)
    }
}