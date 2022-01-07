package com.mrn.moviedb.framework.dataSource

import com.mrn.core.common.Constants
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import com.mrn.moviedb.framework.network.MovieApi
import com.mrn.moviedb.utils.toPage
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NetMovieDataSource @Inject constructor(private val movieApi: MovieApi) : MovieDataSource {
    override suspend fun getPopularMovieList(pageNumber: Int): Page<Movie> {
        return movieApi.getPopularMovies(Constants.API_KEY, pageNumber)
            .subscribeOn(Schedulers.io())
            .map { it.toPage() }
            .blockingFirst()
    }
}