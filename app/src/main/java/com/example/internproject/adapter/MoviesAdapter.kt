package com.example.internproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.internproject.R
import com.example.internproject.databinding.ItemRowBinding
import com.example.internproject.response.MovieListResponse
import com.example.internproject.utils.Constants.IMG_URL
import com.example.internproject.utils.OnItemClick

class MoviesAdapter(private val onItemClick: OnItemClick) :
    PagingDataAdapter<MovieListResponse.MovieResult, MoviesAdapter.ViewHolder>(differCallBack) {


    private lateinit var binding: ItemRowBinding
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRowBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieListResponse.MovieResult) {
            binding.apply {
                txtTitle.text = item.original_title
                txtRate.text = item.vote_average.toString()
                txtLang.text = item.original_language
                txtDate.text = item.release_date
                val moviePoster = IMG_URL + item.poster_path
                imgMovie.load(moviePoster) {
                    crossfade(true)
                    placeholder(R.drawable.movie_loading)
                    scale(Scale.FILL)
                }
                root.setOnClickListener {
                    onItemClick.onClick(item)
                }
            }
        }
    }

    companion object {
        private val differCallBack =
            object : DiffUtil.ItemCallback<MovieListResponse.MovieResult>() {
                override fun areItemsTheSame(
                    oldItem: MovieListResponse.MovieResult,
                    newItem: MovieListResponse.MovieResult
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieListResponse.MovieResult,
                    newItem: MovieListResponse.MovieResult
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}