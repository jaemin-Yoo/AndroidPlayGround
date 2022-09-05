package com.jaemin.androidplayground.ui.sub

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaemin.androidplayground.databinding.FragmentSubSecondBinding

class SubSecondFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("SubSecondFragment","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SubSecondFragment","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSubSecondBinding.inflate(inflater, container, false)
        context ?: return binding.root
        Log.d("SubSecondFragment","onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SubSecondFragment","onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("SubSecondFragment","onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SubSecondFragment","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SubSecondFragment","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SubSecondFragment","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SubSecondFragment","onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("SubSecondFragment","onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SubSecondFragment","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SubSecondFragment","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("SubSecondFragment","onDetach")
    }
}