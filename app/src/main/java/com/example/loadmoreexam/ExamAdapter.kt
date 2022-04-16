package com.example.loadmoreexam

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoreexam.databinding.ItemExamBinding
import com.example.loadmoreexam.model.Exam

class ExamAdapter : RecyclerView.Adapter<ExamAdapter.ViewHolder>() {
    private var listExam = arrayListOf<Exam>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: List<Exam>) {
        listExam.clear()
        listExam.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listExam[position])
    }



    override fun getItemCount(): Int = listExam.size

    class ViewHolder(private val binding: ItemExamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exam: Exam) {
            binding.tvName.text = exam.name
        }
    }


}