package com.mrn.moviedb.framework.network

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.mrn.core.data.MovieDataSource
import com.mrn.core.domain.Movie
import com.mrn.core.domain.MovieDetails
import com.mrn.core.domain.Page
import com.mrn.moviedb.data.MovieRepository
import com.mrn.moviedb.framework.dataSource.NetMovieDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class NetworkService @Inject constructor() : Service(), MovieDataSource {

    @Inject
    lateinit var netMovieDataSource: NetMovieDataSource
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onBind(intent: Intent): IBinder {
        return LocalBinder()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }

    inner class LocalBinder : Binder() {
        fun getService(): NetworkService = this@NetworkService
    }

    override suspend fun getPopularMovieList(pageNumber: Int): Flow<Page<Movie>> {
        return channelFlow {
            withContext(serviceScope.coroutineContext) {
                netMovieDataSource.getPopularMovieList(pageNumber)
                    .collect {
                        channel.send(it)
                        channel.close()
                    }
            }
        }
    }

    override suspend fun getMovieDetails(id: Int): Flow<MovieDetails> {
        return channelFlow {
            withContext(serviceScope.coroutineContext) {
                netMovieDataSource.getMovieDetails(id)
                    .collect {
                        channel.send(it)
                        channel.close()
                    }
            }
        }
    }
}