package com.example.internproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import coil.size.Scale
import com.example.internproject.R
import com.example.internproject.databinding.FragmentMovieDetailsBinding
import com.example.internproject.utils.Constants.IMG_URL
import com.example.internproject.viewModel.MovieDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment(private val movie_id: Int) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

//    @Inject
//    lateinit var apiRepository: ApiRepository

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (movie_id > 0) {
            movieDetailsViewModel.loadMovieDetails(movie_id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        showMovieDetails()

        return binding.root
    }

    private fun showMovieDetails() {
        binding.apply {
            movieDetailsViewModel.detailsMovie.observe(viewLifecycleOwner) { response ->
                val moviePoster = IMG_URL + response.poster_path
                tvMovieName.text = response.original_title
                txtTagline.text = response.tagline
                tvReleaseDate.text = response.release_date
                tvRating.text = response.vote_average.toString()
                tvRuntime.text = response.runtime.toString()
                tvBudget.text = response.budget.toString()
                tvRevenue.text = response.revenue.toString()
                tvOverview.text = response.overview

                imgDetails.load(moviePoster) {
                    crossfade(true)
                    placeholder(R.drawable.movie_loading)
                    scale(Scale.FILL)
                }
                imgBackground.load(moviePoster) {
                    crossfade(true)
                    placeholder(R.drawable.movie_loading)
                    scale(Scale.FILL)
                }
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}