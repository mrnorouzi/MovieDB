package com.mrn.moviedb.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mrn.core.common.Constants
import com.mrn.core.data.MovieDataSource
import com.mrn.moviedb.R
import com.mrn.moviedb.data.MovieRepository
import com.mrn.moviedb.framework.dataSource.NetMovieDataSource
import com.mrn.moviedb.framework.network.LoggerInterceptor
import com.mrn.moviedb.framework.network.MovieApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }
}

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(LoggerInterceptor())
            .build()
    }

    single {
        GsonConverterFactory.create()
    }

    single {
        RxJava2CallAdapterFactory.create()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(MovieApi::class.java)
    }
}

val repositoryModule = module {
    single<MovieDataSource> { NetMovieDataSource(get()) }
    single { MovieRepository(get()) }
}

val uiModule = module {

    factory(named("glide-fragment")) { (fragment: Fragment) ->
        Glide.with(fragment)
    }

    factory(named("glide-view")) { (view: View) ->
        Glide.with(view)
    }

    factory(named("glide-activity")) { (activity: Activity) ->
        Glide.with(activity)
    }
}


