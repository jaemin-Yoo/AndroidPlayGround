package com.jaemin.androidplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jaemin.androidplayground.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSecondBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.btnMove.setOnClickListener {
            val direction =
                SecondFragmentDirections.actionSecondFragmentToThirdFragment()
            findNavController().navigate(direction)
        }

        return binding.root
    }
}