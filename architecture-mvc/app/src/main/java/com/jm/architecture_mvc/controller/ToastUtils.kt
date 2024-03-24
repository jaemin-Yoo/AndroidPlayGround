package com.jm.architecture_mvc.controller

import android.content.Context
import android.widget.Toast

object ToastUtils {
    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}