package com.example.myapplication.viewmodels

import androidx.databinding.ObservableField

class DataBindingViewModel {
    val name = ObservableField("")

    init{
        name.set("Yoo")
    }

    fun nameClick(){
        if (name.get() == "Yoo"){
            name.set("Jaemin")
        } else{
            name.set("Yoo")
        }
    }
}