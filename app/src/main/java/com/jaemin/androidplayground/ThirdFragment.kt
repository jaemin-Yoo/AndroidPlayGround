package com.jaemin.androidplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.androidplayground.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }
}