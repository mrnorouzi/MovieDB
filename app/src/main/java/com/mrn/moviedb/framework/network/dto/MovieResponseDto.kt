package com.mrn.moviedb.framework.network.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("adult") val isAdult: Boolean?,
    @SerializedName("video") val isVideo: Boolean?,
    @SerializedName("vote_count") val votes: Int?,
    @SerializedName("vote_average") val votesAverage: Float?,
    @SerializedName("popularity") val popularity: Float?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
)