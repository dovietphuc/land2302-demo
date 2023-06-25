package com.example.demonavigationfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demonavigationfragment.databinding.FragmentStudentListBinding

class StudentFragment : Fragment(R.layout.fragment_student_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStudentListBinding.bind(view)

        binding.list.adapter = StudentAdapter(DATA_LIST.asList())
        binding.list.layoutManager = LinearLayoutManager(requireContext())
    }
}