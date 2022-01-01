package com.mrn.moviedb.framework.dataSource

import com.mrn.core.common.Constants
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import com.mrn.moviedb.framework.network.MovieApi
import com.mrn.moviedb.utils.toPage
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NetMovieDataSource(val movieApi: MovieApi) : MovieDataSource {
    override fun getPopularMovieList(pageNumber: Int): Observable<Page<Movie>> {
        return movieApi.getPopularMovies(Constants.API_KEY, pageNumber)
            .subscribeOn(Schedulers.io())
            .map { it.toPage() }
    }
}