package com.mrn.moviedb.ui.screens.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mrn.core.domain.MovieDetails
import com.mrn.moviedb.data.MovieRepository
import com.mrn.moviedb.databinding.FragmentMovieDetailsBinding
import com.mrn.moviedb.domain.viewmodels.MovieDetailsViewModel
import com.mrn.moviedb.framework.network.NetworkService
import com.mrn.moviedb.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onServiceBound(service: NetworkService) {
        val movieRepository = MovieRepository(service)
        viewModel.setRepository(movieRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater)
            .apply {
                viewModel = this@MovieDetailsFragment.viewModel
                lifecycleOwner = this@MovieDetailsFragment
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.updateState(args.movie)
        }
    }
}
