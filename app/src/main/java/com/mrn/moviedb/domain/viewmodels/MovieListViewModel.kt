package com.mrn.moviedb.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mrn.core.domain.Movie
import com.mrn.moviedb.common.LoadingStates
import com.mrn.moviedb.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(/*private val movieRepository: MovieRepository*/) :
    ViewModel() {


    private val movieRepositoryState: MutableStateFlow<MovieRepository?> = MutableStateFlow(null)
    private val _loadingState = MutableStateFlow(LoadingStates.IDLE)
    val loadingState
        get() = _loadingState.asStateFlow()
    private val _firstLoadingState = MutableStateFlow(LoadingStates.LOADING)
    val firstLoadingState
        get() = _firstLoadingState.asStateFlow()
    val movieList: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val pagingDataFlow: MutableStateFlow<Flow<PagingData<Movie>>> = MutableStateFlow(
        flowOf(
            PagingData.empty()
        )
    )

    fun setRepository(movieRepository: MovieRepository) {
        movieRepositoryState.value = movieRepository
        pagingDataFlow.value = movieRepository.getPopularMoviePageStream()
    }

    fun loadingStateChanged(loadingStates: LoadingStates) {
        _loadingState.value = loadingStates
    }

    fun updateFirstLoadingState(loadingStates: LoadingStates){
        _firstLoadingState.value = loadingStates
    }
}