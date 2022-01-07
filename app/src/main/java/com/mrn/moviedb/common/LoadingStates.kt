package com.mrn.moviedb.common

data class LoadingStates private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val IDLE = LoadingStates(Status.IDLE)
        val LOADING = LoadingStates(Status.LOADING)
        fun error(msg: String?) = LoadingStates(Status.FAILED, msg)
    }

    enum class Status {
        LOADING,
        IDLE,
        FAILED
    }
}
