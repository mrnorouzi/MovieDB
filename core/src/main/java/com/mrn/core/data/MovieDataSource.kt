package com.mrn.core.data

import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import io.reactivex.Observable

interface MovieDataSource {
    suspend fun getPopularMovieList(pageNumber: Int): Page<Movie>
}