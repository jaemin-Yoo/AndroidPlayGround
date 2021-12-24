package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.TextListAdapter
import com.example.myapplication.data.TextEntity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivitySecondBinding
import com.example.myapplication.viewmodels.SecondViewModel
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.recycler_item.*

/**
 *  ViewModel + LiveData + DataBinding + Repository + Room + RecyclerView
 */

class SecondActivity : AppCompatActivity() {

    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySecondBinding>(this, R.layout.activity_second)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val mAdapter = TextListAdapter(this)
        recyclerView.apply{
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        // 데이터가 변경될 때 반응할 수 있음.
        // RecyclerView 는 Databinding으로 처리가 불가능하기 때문에 observe 함수 사용
        viewModel.allText.observe(this, Observer { text ->
            text?.let {
                mAdapter.setContents(it)
            }
        })

        btn_save.setOnClickListener {
            viewModel.insert(
                TextEntity(null, et_name.text.toString())
            )
        }

        btn_deleteAll.setOnClickListener {
            viewModel.deleteAll()
        }

        val testValue = intent.getStringExtra("testValue")
        Log.d("SecondActivity", testValue!!)

        val secondIntent = Intent()
        secondIntent.putExtra("testValue","Wow")
        setResult(RESULT_OK, secondIntent)
    }
}