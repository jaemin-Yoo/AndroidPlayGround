package com.jaemin.androidplayground.ui.sub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jaemin.androidplayground.R

class SubFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_first)
        Log.d("SubFirstActivity", "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SubFirstActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SubFirstActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SubFirstActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SubFirstActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SubFirstActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SubFirstActivity", "onDestroy")
    }
}