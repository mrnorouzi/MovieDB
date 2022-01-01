package com.mrn.core.domain

data class Page<T>(
    val page: Int,
    var totalResults: Int,
    var totalPages: Int,
    var results: List<T>? = null
)