package com.example.loadmoreexam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoreexam.databinding.ExamLoadStateViewBinding

class ExamLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ExamLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            ExamLoadStateViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), retry
        )
    }


    class LoadStateViewHolder(private val binding: ExamLoadStateViewBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun onBind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.txtError.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState !is LoadState.Loading
            binding.txtError.isVisible = loadState !is LoadState.Loading
        }

    }

}