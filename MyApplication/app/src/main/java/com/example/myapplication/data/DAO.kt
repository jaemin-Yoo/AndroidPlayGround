package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {

    @Query("SELECT * FROM entity")
    fun getAll(): LiveData<List<Entity>>

    @Insert
    fun insert(entity: Entity)

    @Update
    fun update(entity: Entity)

    @Delete
    fun delete(entity: Entity)
}