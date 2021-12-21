package com.example.myapplication.example

import android.util.Log

class StudyExampleFunction {

    val TAG = "StudyExampleFunction"

    fun forLoopExample(){
        // in, ..
        for (i in 1..3){
            Log.d(TAG, "i: $i")
        }

        // in, until
        for (i in 1 until 3){
            Log.d(TAG, "i: $i")
        }

        // in, .., step
        for (i in 1..5 step(2)){
            Log.d(TAG, "i: $i")
        }

        for (i in 5 downTo 1 step(2)){
            Log.d(TAG, "i: $i")
        }

        // in list
        val list = arrayListOf("A", "B", "C")
        for (i in list){
            Log.d(TAG, "i: $i")
        }

        // in list indices
        for (i in list.indices){
            Log.d(TAG, "i: $i")
        }

        // in list and list indices
        for ((i, v) in list.withIndex()){
            Log.d(TAG, "i: $i")
            Log.d(TAG, "v: $v")
        }
    }
}