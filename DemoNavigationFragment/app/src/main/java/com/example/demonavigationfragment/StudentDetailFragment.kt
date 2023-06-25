package com.example.demonavigationfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.demonavigationfragment.databinding.FragmentStudentDetailBinding

class StudentDetailFragment(private var id: Int = -1)
    : Fragment(R.layout.fragment_student_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStudentDetailBinding.bind(view)
        id = requireArguments().getInt("STUDENT_ID_KEY", id)
        if(id != -1) {
            binding.txtName.text = DATA_LIST[id]
        }
    }
}