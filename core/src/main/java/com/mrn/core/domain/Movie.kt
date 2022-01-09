package com.mrn.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String?,
    val title: String?,
    val isVideo: Boolean?,
    val isAdult: Boolean?,
    val votes: Int?,
    val votesAverage: Float?,
    val popularity: Float?,
    val backdropPath: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val originalLanguage: String?,
    val overview: String?,
    val releaseDate: String?,
) : Parcelable