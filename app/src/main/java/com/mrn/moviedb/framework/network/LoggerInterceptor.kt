package com.mrn.moviedb.framework.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.d("=======================")
        Timber.d("request:\nmethod: ${request.method()} --- url:${request.url()}")
        val response = chain.proceed(request)
        Timber.d("response:\n$response")
        Timber.d("=======================")
        return response
    }
}