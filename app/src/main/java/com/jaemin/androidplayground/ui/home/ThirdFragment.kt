package com.jaemin.androidplayground.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.androidplayground.databinding.FragmentThirdBinding
import com.jaemin.androidplayground.ui.sub.SubActivity

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.btnMove.setOnClickListener {
            val intent = Intent(context, SubActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}