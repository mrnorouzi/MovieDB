package com.mrn.moviedb.ui.movieList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrn.core.common.disposeWith
import com.mrn.core.data.MovieRepository
import com.mrn.moviedb.R
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Logger
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        GlobalScope.launch {
        val disposable = CompositeDisposable()
        try {
            get<MovieRepository>()
                .getPopularMovieList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.results?.size
                    it.page
                }, { error ->
                    error.printStackTrace()
                }
                ).disposeWith(disposable)
        } catch (ex: Throwable) {
            ex.printStackTrace()
        }
//        }

    }
}