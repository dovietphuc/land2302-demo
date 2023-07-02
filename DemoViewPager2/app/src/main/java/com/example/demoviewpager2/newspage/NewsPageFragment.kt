package com.example.demoviewpager2.newspage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoviewpager2.R
import com.example.demoviewpager2.databinding.FragmentNewsPageBinding

class NewsPageFragment(val url: String) : Fragment(R.layout.fragment_news_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsPageBinding.bind(view)

        binding.webView.loadUrl(url)
    }
}