package com.mrn.moviedb.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mrn.core.common.Constants
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import kotlinx.coroutines.flow.first

private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val dataSource: MovieDataSource,
    private val query: String
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = dataSource.getPopularMovieList(position/*, params.loadSize*/)
            val list = response.first().results
            val nextKey = if (list?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / Constants.NETWORK_PAGE_SIZE)
            }
            return LoadResult.Page(
                data = list ?: listOf(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            PagingSource.LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
