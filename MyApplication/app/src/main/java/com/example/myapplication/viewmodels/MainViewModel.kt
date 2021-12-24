package com.example.myapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.CountEntity
import com.example.myapplication.data.TextEntity
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val name = ObservableField("")
    var count = MutableLiveData<Int>()
    var roomCount = MutableLiveData<Int>()

    private val repository: Repository = Repository(AppDatabase.getDatabase(application, viewModelScope))
    val allCount: LiveData<Int> = repository.allCount

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
        roomCount.value = roomCount.value?.plus(1)
        updateCount(CountEntity(0, roomCount.value!!))
    }

    fun decrease(){
        count.value = count.value?.minus(1)
        roomCount.value = roomCount.value?.minus(1)
        updateCount(CountEntity(0, roomCount.value!!))
    }

    private fun updateCount(countEntity: CountEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.countUpdate(countEntity)
    }
}