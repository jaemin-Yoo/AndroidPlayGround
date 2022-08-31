package com.jaemin.androidplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.androidplayground.databinding.FragmentSubSecondBinding

class SubSecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSubSecondBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }
}