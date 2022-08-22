package com.jaemin.androidplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jaemin.androidplayground.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.btnMove.setOnClickListener {
            val direction =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            findNavController().navigate(direction)
        }

        return binding.root
    }
}