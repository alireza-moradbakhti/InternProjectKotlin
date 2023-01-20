package com.example.internproject.repository

import com.example.internproject.api.ApiService
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped    //scoped objects lives as long as its components
class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPopularMovieList(page: Int) = apiService.getPopularMoviesList(page)
    suspend fun getMovieDetails(movie_id: Int) = apiService.getMovieDetails(movie_id)
}