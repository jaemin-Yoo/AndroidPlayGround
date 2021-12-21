package com.example.myapplication.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.DAO
import com.example.myapplication.data.Entity

class Repository(application: Application) {
    private val dao: DAO
    private val list: LiveData<List<Entity>>

    init {
        var db = AppDatabase.getInstance(application)
        dao = db!!.dao()
        list = db.dao().getAll()
    }

    fun insert(entity: Entity){
        dao.insert(entity)
    }

    fun delete(entity: Entity){
        dao.delete(entity)
    }

    fun getAll(): LiveData<List<Entity>>{
        return dao.getAll()
    }
}