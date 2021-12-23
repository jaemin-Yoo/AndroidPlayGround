package com.example.myapplication.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.CountEntity
import com.example.myapplication.data.TextDao
import com.example.myapplication.data.TextEntity

class Repository(mDatabase: AppDatabase) {
    private val textDao = mDatabase.textDao()
    private val countDao = mDatabase.countDao()

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
}