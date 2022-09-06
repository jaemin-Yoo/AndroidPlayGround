package com.jaemin.androidplayground.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaemin.androidplayground.databinding.FragmentThirdBinding
import com.jaemin.androidplayground.ui.compose.ComposeActivity
import com.jaemin.androidplayground.ui.sub.SubActivity

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.btnCheckLifecycle.setOnClickListener {
            val intent = Intent(context, SubActivity::class.java)
            startActivity(intent)
        }

        binding.btnCompose.setOnClickListener {
            val intent = Intent(context, ComposeActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}