package com.example.demoviewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demoviewpager2.page1.PageOneFragment
import com.example.demoviewpager2.page2.PageTwoFragment
import com.example.demoviewpager2.page3.PageThreeFragment

class PageFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PageOneFragment()
            1 -> PageTwoFragment()
            else -> PageThreeFragment()
        }
    }
}