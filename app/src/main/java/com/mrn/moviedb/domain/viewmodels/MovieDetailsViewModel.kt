package com.mrn.moviedb.domain.viewmodels

import androidx.lifecycle.ViewModel
import com.mrn.core.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor() :
    ViewModel() {

    private val _movieState = MutableStateFlow<Movie?>(null)
    val movieState
        get() = _movieState.asStateFlow()

    fun updateState(movie: Movie) {
        _movieState.update { movie }
    }
}
//
//open class UiState {
//
//    object NoData : UiState()
//    class Data(val movie: Movie) : UiState()
//}