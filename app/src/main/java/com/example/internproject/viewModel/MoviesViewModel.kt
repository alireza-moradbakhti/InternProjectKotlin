package com.example.internproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.internproject.paging.MoviesPagingSource
import com.example.internproject.repository.ApiRepository
import com.example.internproject.response.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {


    val movieList = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)



}