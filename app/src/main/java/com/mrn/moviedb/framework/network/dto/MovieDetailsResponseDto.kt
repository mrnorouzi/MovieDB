package com.mrn.moviedb.framework.network.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("genres") val genres: List<GenreDto>?
)

data class GenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?
)