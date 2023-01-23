package com.example.internproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internproject.response.MovieListResponse
import dagger.hilt.android.lifecycle.HiltViewModel


class ItemRowViewModel : ViewModel() {

    val movie = MutableLiveData<MovieListResponse.MovieResult>()

}