package com.example.mvvmexample.dialog

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.mvvmexample.R
import com.example.mvvmexample.data.UserEntity
import com.example.mvvmexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.user_dialog.*
import java.util.*

class UserDialog(mContext: Context) : Dialog(mContext) {
    private val viewModel: MainViewModel = MainViewModel(mContext.applicationContext as Application)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_dialog)

        // 다이얼로그의 배경을 투명으로 만든다.
        Objects.requireNonNull(window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 버튼 리스너 설정
        button_save.setOnClickListener {
            // '확인' 버튼 클릭시 data insert
            viewModel.insert(
                UserEntity(user_name.text.toString(),
                user_gender.text.toString(),user_birth.text.toString())
            )
            // Custom Dialog 종료
            dismiss()
        }
        button_cancel.setOnClickListener {
            // '취소' 버튼 클릭시
            // Custom Dialog 종료
            dismiss()
        }
    }
}