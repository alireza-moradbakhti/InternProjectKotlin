package com.example.internproject.response

data class MovieListResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
) {
    data class MovieResult(
        val adult: Boolean,
        val backdrop_path: String ,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: String,
        val vote_count: Int
    )
}