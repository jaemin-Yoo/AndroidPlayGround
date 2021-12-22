package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentBlankBinding
import com.example.myapplication.viewmodels.MainViewModel

class BlankFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentBlankBinding
    private var fragment_number = "Fragment"

    fun newInstance(fragment_number: Int): BlankFragment{
        val fragment = BlankFragment()
        val args = Bundle()

        args.putString("fragment_number", fragment_number.toString())

        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            fragment_number += arguments!!.getString("fragment_number")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        activity?.let{
            viewModel = ViewModelProvider(it).get(MainViewModel::class.java)
            binding.fragmentNumber = fragment_number
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }

        return binding.root
    }
}