package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.example.StudyExampleFunction
import com.example.myapplication.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MVVM Pattern Practice (ViewModel + LiveData + DataBinding)
 */

class MainActivity : AppCompatActivity() {

    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = model

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                val testValue = it.data?.getStringExtra("testValue")
                Log.d("MainActivity", testValue!!)
            }
        }

        move_btn.setOnClickListener {
            val mainIntent = Intent(this, SecondActivity::class.java)
            mainIntent.putExtra("testValue","Good")
            result.launch(mainIntent)
        }

        // for loop Example
        var exampleFun = StudyExampleFunction()
        exampleFun.forLoopExample()
    }

    private fun setViewPager(){

    }
}