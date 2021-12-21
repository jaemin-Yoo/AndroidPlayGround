package com.example.myapplication.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.TextEntity
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel(application: Application): AndroidViewModel(application) {
    var save_text: ObservableField<String> = ObservableField("Save")
    val mApplication = application

    val repository: Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))
    var allText: LiveData<List<TextEntity>> = repository.allText

    fun onClickButton(){
        // TODO: 클릭 시 Room에 데이터 추가
        Toast.makeText(mApplication, "Click!", Toast.LENGTH_SHORT).show()
    }

    fun insert(textEntity: TextEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(textEntity)
    }
}