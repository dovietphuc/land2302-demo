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

        binding.list.adapter =
            StudentAdapter(DATA_LIST.asList(), object :StudentAdapter.OnItemClickListener {
                override fun onItemClick(pos: Int) {
                    val bundle = Bundle()
                    bundle.putInt("STUDENT_ID_KEY", pos)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.host_fragment, StudentDetailFragment::class.java, bundle)
                        .addToBackStack("detail_fragment")
                        .commit()
                }
            })
        binding.list.layoutManager = LinearLayoutManager(requireContext())
    }
}