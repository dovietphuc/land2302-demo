package com.example.demofragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.demofragment.databinding.FragmentDemoBinding

class DemoFragment(var message: String = "This is demo fragment") : Fragment(R.layout.fragment_demo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDemoBinding.bind(view)
        message = requireArguments().getString("MESSAGE_KEY", message)
        binding.txtMessage.text = message
    }
}