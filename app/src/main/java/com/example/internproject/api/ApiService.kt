package com.example.internproject.api

import com.example.internproject.response.MovieDetailsResponse
import com.example.internproject.response.MovieListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMoviesList(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = "0a5d7f1f9c9fca1fa44e067c784d6920"
    ): Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = "0a5d7f1f9c9fca1fa44e067c784d6920"
    ): Response<MovieDetailsResponse>
}