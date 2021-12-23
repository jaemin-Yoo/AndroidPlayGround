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
import androidx.lifecycle.Observer
import com.example.myapplication.adapters.ViewPagerAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.example.StudyExampleFunction
import com.example.myapplication.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 * MVVM Pattern Practice (ViewModel + LiveData + DataBinding + ViewPager2 + FragmentStateAdapter)
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

        setViewPager()

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
        val exampleFun = StudyExampleFunction()
        exampleFun.forLoopExample()
    }

    private fun setViewPager(){
        val adapter = ViewPagerAdapter(this)
        adapter.setList(getFragmentList())
        viewpager.adapter = adapter
    }

    private fun getFragmentList(): List<BlankFragment> {
        return listOf(BlankFragment().newInstance(1), BlankFragment().newInstance(2), BlankFragment().newInstance(3))
    }
}