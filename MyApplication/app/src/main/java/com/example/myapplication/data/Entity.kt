package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entity(
    var title: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
