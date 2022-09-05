package com.jaemin.androidplayground.ui.sub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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