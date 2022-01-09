package com.mrn.core.domain

data class MovieDetails(
    val id: Int,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val genres: List<GenreSingle>?
)

data class GenreSingle(val id: Int, val name: String?)