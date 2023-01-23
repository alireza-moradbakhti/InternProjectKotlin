package com.example.internproject.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import coil.load
import coil.size.Scale
import com.example.internproject.R
import com.example.internproject.databinding.ItemRowBinding
import com.example.internproject.paging.MoviesPagingSource
import com.example.internproject.repository.ApiRepository
import com.example.internproject.response.MovieListResponse
import com.example.internproject.utils.Constants
import com.example.internproject.utils.OnItemClick
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: ApiRepository,
) : ViewModel() {


    val movieList = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)





}