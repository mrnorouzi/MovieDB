package com.mrn.core.data

import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import io.reactivex.Observable

class MovieRepository(private val dataSource: MovieDataSource) {
    fun getPopularMovieList(pageNumber: Int): Observable<Page<Movie>> =
        dataSource.getPopularMovieList(pageNumber)

}