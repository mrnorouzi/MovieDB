package com.mrn.moviedb.framework.network.dto

import com.google.gson.annotations.SerializedName

data class PageResponseDto(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("results") var results: List<MovieResponseDto>? = null
)