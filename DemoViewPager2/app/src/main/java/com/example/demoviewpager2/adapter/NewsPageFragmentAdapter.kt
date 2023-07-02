package com.example.demoviewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demoviewpager2.newspage.NewsPageFragment

class NewsPageFragmentAdapter(val data: List<String>,
                              fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return NewsPageFragment(data[position])
    }
}