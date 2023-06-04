package com.example.demorecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class MainActivity : AppCompatActivity() {

    val mStudentList = ArrayList<Student>()

    lateinit var mRcvStudent: RecyclerView
    lateinit var mStudentAdapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))
        mStudentList.add(Student("Do Viet Phuc", Date(1997, 5, 20), R.drawable.baseline_person_24))
        mStudentList.add(Student("Nguyen Huy Tu", Date(1999, 1, 14), R.drawable.baseline_person_2_24))
        mStudentList.add(Student("Phan Van Truong", Date(1998, 7, 15), R.drawable.baseline_person_24))

        mRcvStudent = findViewById(R.id.rcv_students)
        mStudentAdapter = StudentListAdapter(mStudentList)

        mRcvStudent.layoutManager = LinearLayoutManager(this)
        mRcvStudent.adapter = mStudentAdapter


        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if(direction == ItemTouchHelper.LEFT){
                        mStudentList.removeAt(viewHolder.adapterPosition)
                        mStudentAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                }

            })
        itemTouchHelper.attachToRecyclerView(mRcvStudent)
    }
}