package com.example.myapplication.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.TextDao
import com.example.myapplication.data.TextEntity

class Repository(mDatabase: AppDatabase) {
    private val textDao = mDatabase.textDao()
    val allText: LiveData<List<TextEntity>> = textDao.getAll()

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

    suspend fun insert(textEntity: TextEntity){
        textDao.insert(textEntity)
    }

    suspend fun deleteAll(){
        textDao.deleteAll()
    }
}