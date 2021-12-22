package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.BlankFragment
import kotlin.collections.ArrayList


class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private var list = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

    fun addFragment(fragment: Fragment){
        list.add(fragment)
    }

    fun setList(fragments: List<BlankFragment>){
        list.clear()
        list.addAll(fragments)
    }
}