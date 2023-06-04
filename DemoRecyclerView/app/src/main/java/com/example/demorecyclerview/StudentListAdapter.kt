package com.example.demorecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class StudentListAdapter(var mData: List<Student> = ArrayList())
                                    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    val dateFormat = SimpleDateFormat("dd/MM/YYYY")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.student_item, parent, false)
        val viewHolder = StudentViewHolder(view)
        Log.d("PhucDVb", "onCreateViewHolder: $viewHolder")
        return viewHolder
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        Log.d("PhucDVb", "onBindViewHolder: $holder - $position")
        val student = mData[position]
        holder.mImgAvatar.setImageResource(student.avatarResId)
        holder.mTvName.text = student.name
        holder.mTvDateOfBirth.text = dateFormat.format(student.dateOfBirth)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class StudentViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        lateinit var mImgAvatar: ImageView
        lateinit var mTvName: TextView
        lateinit var mTvDateOfBirth: TextView

        init {
            mImgAvatar = viewItem.findViewById(R.id.img_avatar)
            mTvName = viewItem.findViewById(R.id.tv_name)
            mTvDateOfBirth = viewItem.findViewById(R.id.tv_date_of_birth)
        }
    }
}