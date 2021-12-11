package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = DataBindingViewModel()

        // for loop Example
        forLoopExample()

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                val testValue = it.data?.getStringExtra("testValue")
                Log.d("MainActivity", testValue!!)
            }
        }

        button.setOnClickListener {
            val mainIntent = Intent(this, SecondActivity::class.java)
            mainIntent.putExtra("testValue","Good")
            result.launch(mainIntent)
        }
    }

    private fun forLoopExample(){
        // in, ..
        for (i in 1..3){
            Log.d("MainActivity", "i: $i")
        }

        // in, until
        for (i in 1 until 3){
            Log.d("MainActivity", "i: $i")
        }

        // in, .., step
        for (i in 1..5 step(2)){
            Log.d("MainActivity", "i: $i")
        }

        for (i in 5 downTo 1 step(2)){
            Log.d("MainActivity", "i: $i")
        }

        // in list
        val list = arrayListOf("A", "B", "C")
        for (i in list){
            Log.d("MainActivity", "i: $i")
        }

        // in list indices
        for (i in list.indices){
            Log.d("MainActivity", "i: $i")
        }

        // in list and list indices
        for ((i, v) in list.withIndex()){
            Log.d("MainActivity", "i: $i")
            Log.d("MainActivity", "v: $v")
        }
    }
}