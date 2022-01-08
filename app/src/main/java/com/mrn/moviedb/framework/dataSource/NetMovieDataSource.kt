package com.mrn.moviedb.framework.dataSource

import com.mrn.core.common.Constants
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import com.mrn.moviedb.framework.network.MovieApi
import com.mrn.moviedb.framework.network.dto.PageResponseDto
import com.mrn.moviedb.utils.toPage
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetMovieDataSource @Inject constructor(private val movieApi: MovieApi) : MovieDataSource {
    override suspend fun getPopularMovieList(pageNumber: Int): Flow<Page<Movie>> {
        return movieApi.getPopularMovies(Constants.API_KEY, pageNumber)
            .map(PageResponseDto::toPage)
    }
}