package com.mrn.core.data

import com.mrn.core.domain.Movie
import com.mrn.core.domain.MovieDetails
import com.mrn.core.domain.Page
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    suspend fun getPopularMovieList(pageNumber: Int): Flow<Page<Movie>>

    suspend fun getMovieDetails(id: Int): Flow<MovieDetails>
}