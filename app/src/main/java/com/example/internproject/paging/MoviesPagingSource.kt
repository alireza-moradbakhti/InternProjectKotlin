package com.example.internproject.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.internproject.repository.ApiRepository
import com.example.internproject.response.MovieListResponse
import retrofit2.HttpException

class MoviesPagingSource(private val repository: ApiRepository) :
    PagingSource<Int, MovieListResponse.MovieResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListResponse.MovieResult> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getPopularMovieList(currentPage)
            val data = response.body()!!.results
            val responseData = mutableListOf<MovieListResponse.MovieResult>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpException) {
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieListResponse.MovieResult>): Int? {
        return null
    }
}