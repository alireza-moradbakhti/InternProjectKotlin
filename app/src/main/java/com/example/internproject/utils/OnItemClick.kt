package com.example.internproject.utils

import com.example.internproject.response.MovieListResponse
import dagger.Provides
import javax.inject.Singleton

interface OnItemClick {

    fun onClick(item: MovieListResponse.MovieResult)
}