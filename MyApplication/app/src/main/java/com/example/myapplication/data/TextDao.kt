package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TextDao {

    @Query("SELECT * FROM text_table ORDER BY title ASC")
    fun getAll(): LiveData<List<TextEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(textEntity: TextEntity)

    @Delete
    suspend fun delete(textEntity: TextEntity)
}