package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val testValue = intent.getStringExtra("testValue")
        Log.d("SecondActivity", testValue!!)

        val secondIntent = Intent()
        secondIntent.putExtra("testValue","Wow")
        setResult(RESULT_OK, secondIntent)
        finish()
    }
}