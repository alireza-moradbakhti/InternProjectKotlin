package com.example.internproject.utils

import com.example.internproject.response.MovieListResponse

interface OnItemClick {
    fun onClick(item: MovieListResponse.MovieResult)
}