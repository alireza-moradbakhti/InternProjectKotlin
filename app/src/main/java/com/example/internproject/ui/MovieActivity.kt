package com.example.internproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internproject.adapter.LoadMoreAdapter
import com.example.internproject.adapter.MoviesAdapter
import com.example.internproject.databinding.ActivityMovieBinding
import com.example.internproject.repository.ApiRepository
import com.example.internproject.response.MovieListResponse
import com.example.internproject.utils.Constants.TAG
import com.example.internproject.utils.OnItemClick
import com.example.internproject.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MovieActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityMovieBinding

//    @Inject
//    lateinit var apiRepository: ApiRepository

    private val movieViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMovieBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        val moviesAdapter = MoviesAdapter(this)
        binding.apply {
            lifecycleScope.launchWhenCreated {
                movieViewModel.movieList.collect {
                    moviesAdapter.submitData(it)

                }
            }

            rvMovie.apply {
                layoutManager = LinearLayoutManager(this@MovieActivity)
                adapter = moviesAdapter
            }
            lifecycleScope.launchWhenCreated {
                moviesAdapter.loadStateFlow.collect {
                    val state = it.refresh
                    prgMovie.isVisible = state is LoadState.Loading
                }
            }
            rvMovie.adapter = moviesAdapter.withLoadStateFooter(
                LoadMoreAdapter{
                    moviesAdapter.retry()
                }
            )
        }
    }

    override fun onClick(item: MovieListResponse.MovieResult) {
        val movieDetailsFragment = MovieDetailsFragment(item.id)
        movieDetailsFragment.show(supportFragmentManager, TAG)
    }
}