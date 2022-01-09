package com.mrn.moviedb.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrn.core.domain.Movie
import com.mrn.core.domain.MovieDetails
import com.mrn.moviedb.common.LoadingStates
import com.mrn.moviedb.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor() :
    ViewModel() {

    private val movieRepositoryState: MutableStateFlow<MovieRepository?> = MutableStateFlow(null)
    private val _movieState = MutableStateFlow<Movie?>(null)
    val movieState
        get() = _movieState.asStateFlow()
    private val _movieDetailsState = MutableStateFlow<MovieDetails?>(null)
    val movieDetailsState
        get() = _movieDetailsState.asStateFlow()
    private val _loadingState = MutableStateFlow(LoadingStates.IDLE)
    val loadingState
        get() = _loadingState.asStateFlow()

    init {
        viewModelScope.launch {
            _loadingState.value = LoadingStates.LOADING
            try {
                movieRepositoryState
                    .collect {
                    it?.getMovieDetails(movieState.value?.id ?: 0)?.collect { movieDetails ->
                        _movieDetailsState.value = movieDetails
                        _loadingState.value = LoadingStates.IDLE
                    }
                }
            } catch (ex: Exception) {
                _loadingState.value = LoadingStates.error(ex.message)
            }
        }
    }

    fun updateState(movie: Movie) {
        _movieState.update { movie }
    }

    fun setRepository(movieRepository: MovieRepository) {
        movieRepositoryState.value = movieRepository
    }

}
//
//open class UiState {
//
//    object NoData : UiState()
//    class Data(val movie: Movie) : UiState()
//}