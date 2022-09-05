package com.jaemin.androidplayground.ui.sub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaemin.androidplayground.databinding.FragmentSubFirstBinding

class SubFirstFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("SubFirstFragment","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SubFirstFragment","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSubFirstBinding.inflate(inflater, container, false)
        context ?: return binding.root
        Log.d("SubFirstFragment","onCreateView")

        binding.btnMoveFragment.setOnClickListener {
            val directions =
                SubFirstFragmentDirections.actionSubFirstFragmentToSubSecondFragment()
            findNavController().navigate(directions)
        }

        binding.btnMoveActivity.setOnClickListener {
            val intent = Intent(context, SubFirstActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SubFirstFragment","onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("SubFirstFragment","onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SubFirstFragment","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SubFirstFragment","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SubFirstFragment","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SubFirstFragment","onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("SubFirstFragment","onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SubFirstFragment","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SubFirstFragment","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("SubFirstFragment","onDetach")
    }
}