package com.mrn.moviedb.utils

import com.mrn.core.domain.Movie
import com.mrn.core.domain.Page
import com.mrn.moviedb.framework.network.dto.MovieResponseDto
import com.mrn.moviedb.framework.network.dto.PageResponseDto

internal fun PageResponseDto.toPage(): Page<Movie> {
    return Page(
        this.page,
        this.totalResults,
        this.totalPages,
        this.results?.map(MovieResponseDto::toMovie),
    )
}

internal fun MovieResponseDto.toMovie(): Movie {
    return Movie(
        this.id,
        this.title,
        this.isVideo,
        this.isAdult,
        this.votes,
        this.votesAverage,
        this.popularity,
        this.backdropPath,
        this.originalTitle,
        this.posterPath,
        this.originalLanguage,
        this.overview,
        this.releaseDate,
    )
}