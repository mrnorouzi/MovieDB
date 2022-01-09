package com.mrn.moviedb.ui.screens.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import com.mrn.moviedb.common.LoadingStates
import com.mrn.moviedb.data.MovieRepository
import com.mrn.moviedb.databinding.FragmentMovieListBinding
import com.mrn.moviedb.domain.viewmodels.MovieListViewModel
import com.mrn.moviedb.framework.network.NetworkService
import com.mrn.moviedb.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieListBinding
    private var movieAdapter: MovieAdapter? = null

    private val viewModel: MovieListViewModel by viewModels()

    override fun onServiceBound(service: NetworkService) {
        val movieRepository = MovieRepository(service)
        viewModel.setRepository(movieRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
            .apply {
                viewModel = this@MovieListFragment.viewModel
                lifecycleOwner = this@MovieListFragment
            }

        binding.recyclerMovies.layoutManager =
            object : GridLayoutManager(context, 3, RecyclerView.VERTICAL, false) {}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (movieAdapter == null)
            movieAdapter = MovieAdapter()

        movieAdapter?.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                val action =
                    MovieListFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movie)
                findNavController().navigate(action)
            }
        }

        binding.adapter = movieAdapter

        lifecycleScope.launch {
            movieAdapter?.loadStateFlow
//                ?.distinctUntilChangedBy { it.append }
                ?.collect {
                    when (it.append) {
                        is LoadState.NotLoading -> viewModel.loadingStateChanged(LoadingStates.IDLE)
                        is LoadState.Loading -> viewModel.loadingStateChanged(LoadingStates.LOADING)
                        is LoadState.Error -> viewModel.loadingStateChanged(LoadingStates.error("loading error"))
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collect { pagingData ->
                pagingData.collectLatest {
                    movieAdapter?.submitData(it)
                }
            }
        }
    }
}
