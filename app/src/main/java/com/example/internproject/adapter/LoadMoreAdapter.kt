package com.example.internproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.internproject.databinding.LoadMoreBinding

class LoadMoreAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {
    private lateinit var binding: LoadMoreBinding


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ViewHolder(retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRefresh.setOnClickListener {
                retry()
            }
        }

        fun bind(state: LoadState) {
            binding.apply {
                prgLoadMore.isVisible = state is LoadState.Loading
                txtErrorLoad.isVisible = state is LoadState.Error
                btnRefresh.isVisible = state is LoadState.Error
            }
        }
    }
}