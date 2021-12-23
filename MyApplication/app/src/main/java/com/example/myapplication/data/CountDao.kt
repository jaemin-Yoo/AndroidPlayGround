package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountDao {

    @Query("SELECT count FROM count_table")
    fun getCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(countEntity: CountEntity)

    @Update
    suspend fun update(countEntity: CountEntity)
}