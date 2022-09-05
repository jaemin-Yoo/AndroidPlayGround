package com.jaemin.androidplayground.ui.sub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaemin.androidplayground.databinding.FragmentSubFirstBinding

class SubFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSubFirstBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.btnMove.setOnClickListener {
            val directions =
                SubFirstFragmentDirections.actionSubFirstFragmentToSubSecondFragment()
            findNavController().navigate(directions)
        }

        return binding.root
    }
}