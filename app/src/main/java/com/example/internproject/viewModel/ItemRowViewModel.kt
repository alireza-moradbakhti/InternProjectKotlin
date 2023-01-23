package com.example.internproject.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internproject.response.MovieListResponse

import com.example.internproject.utils.OnItemClick


class ItemRowViewModel(private val onItemClick: OnItemClick) : ViewModel() {

    val movie = MutableLiveData<MovieListResponse.MovieResult>()

    fun onClickItem() {
        onItemClick.onClick(movie.value!!)
    }
}