package com.mrn.moviedb.framework.network

import com.mrn.moviedb.framework.network.dto.PageResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ) : Flow<PageResponseDto>

}