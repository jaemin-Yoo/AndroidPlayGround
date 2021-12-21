package com.example.mvvmexample.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.data.AppDatabase
import com.example.mvvmexample.data.UserEntity
import com.example.mvvmexample.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    var main_text: ObservableField<String> = ObservableField("Main")
    val mApplication = application

    val repository: Repository = Repository(AppDatabase.getDatabase(application,viewModelScope))
    var allUsers: LiveData<List<UserEntity>> = repository.allUsers

    fun onClickButton(){
        // TODO: 클릭 시 Room에 데이터 추가
        Toast.makeText(mApplication, "Click!", Toast.LENGTH_SHORT).show()
    }

    fun insert(userEntity: UserEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(userEntity)
    }
}