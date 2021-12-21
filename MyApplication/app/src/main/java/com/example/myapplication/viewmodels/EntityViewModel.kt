package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.Entity
import com.example.myapplication.repository.Repository

class EntityViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)
    private val items = repository.getAll()

    fun insert(entity: Entity){
        repository.insert(entity)
    }

    fun delete(entity: Entity){
        repository.delete(entity)
    }

    fun getAll(): LiveData<List<Entity>>{
        return items
    }
}