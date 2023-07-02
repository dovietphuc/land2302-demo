package com.example.demoviewpager2.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.demoviewpager2.R
import com.example.demoviewpager2.adapter.NewsPageFragmentAdapter
import com.example.demoviewpager2.adapter.PageFragmentAdapter
import com.example.demoviewpager2.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        val URLS = ArrayList<String>()
    }

    init {
        URLS.add("https://dantri.com.vn/xa-hoi/khoi-to-vu-an-sap-taluy-o-da-lat-khien-2-nguoi-tu-vong-20230702173815350.htm")
        URLS.add("https://dantri.com.vn/the-gioi/vi-sao-ukraine-khong-but-toc-phan-cong-day-lui-nga-tren-tien-tuyen-20230702193553768.htm")
        URLS.add("https://dantri.com.vn/du-lich/co-gai-to-bi-danh-chui-ngu-dot-chu-quan-banh-mi-noi-tieng-ha-noi-noi-gi-20230702163841108.htm")
        URLS.add("https://dantri.com.vn/tam-long-nhan-ai/niem-vui-dau-tien-den-voi-nu-sinh-om-noi-dau-mo-coi-di-thi-tot-nghiep-20230701113158285.htm")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
//        binding.viewpage2.adapter = PageFragmentAdapter(childFragmentManager, lifecycle)
//        binding.viewpage2.adapter = NewsPageFragmentAdapter(URLS, childFragmentManager, lifecycle)

//        binding.tab.tabMode = TabLayout.MODE_AUTO
//
//        TabLayoutMediator(binding.tab, binding.viewpage2)
//        { tab: TabLayout.Tab, position: Int ->
//            tab.setText("Page ${position}")
//        }.attach()

        val navHostFragment = binding.hostHomeFragment.getFragment() as NavHostFragment
        val navController = navHostFragment.navController

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            navController.navigate(menuItem.itemId)
            binding.root.close()
            return@setNavigationItemSelectedListener true
        }
    }
}