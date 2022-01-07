package com.mrn.moviedb.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrn.core.common.Constants
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun getPopularMovieList(pageNumber: Int): Page<Movie> =
        dataSource.getPopularMovieList(pageNumber)


    fun getPopularMoviePageStream(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = Constants.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { MoviePagingSource(dataSource, "") }
    ).flow
}