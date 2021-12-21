package com.example.mvvmexample.repository

import androidx.lifecycle.LiveData
import com.example.mvvmexample.data.AppDatabase
import com.example.mvvmexample.data.UserEntity

class Repository(mDatabase: AppDatabase) {

    private val userDao = mDatabase.userDao()
    val allUsers: LiveData<List<UserEntity>> = userDao.getAlphabetizedUsers()

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

    suspend fun insert(userEntity: UserEntity){
        userDao.insert(userEntity)
    }
}