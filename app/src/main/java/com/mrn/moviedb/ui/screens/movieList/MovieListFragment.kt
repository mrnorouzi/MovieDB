package com.mrn.moviedb.ui.screens.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrn.core.domain.Movie
import com.mrn.moviedb.common.LoadingStates
import com.mrn.moviedb.databinding.FragmentMovieListBinding
import com.mrn.moviedb.domain.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    internal var movieAdapter: MovieAdapter? = null
    private val listViewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
            .apply {
                viewModel = this@MovieListFragment.listViewModel
                lifecycleOwner = this@MovieListFragment
            }

        binding.recyclerMovies.layoutManager =
            object : GridLayoutManager(context, 3, RecyclerView.VERTICAL, false) {}
//            {
//                override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
//                    lp.width = width / 5
//                    return true
//                }
//            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (movieAdapter == null)
            movieAdapter = MovieAdapter()

        binding.adapter = movieAdapter

        lifecycleScope.launch {
        }

        lifecycleScope.launch {
            listViewModel.pagingDataFlow
                .collect {
                    movieAdapter?.submitData(it)
                }
        }
    }
}
