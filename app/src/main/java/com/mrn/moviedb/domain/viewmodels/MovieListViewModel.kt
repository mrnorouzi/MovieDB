package com.mrn.moviedb.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mrn.core.domain.Movie
import com.mrn.moviedb.common.LoadingStates
import com.mrn.moviedb.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _loadingState = MutableStateFlow<LoadingStates>(LoadingStates.IDLE)
    val loadingState
        get() = _loadingState.asStateFlow()
    val movieList: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val pagingDataFlow: Flow<PagingData<Movie>>

    init {
        pagingDataFlow = movieRepository.getPopularMoviePageStream()
    }

    fun loadingStateChanged(loadingStates: LoadingStates) {
        _loadingState.value = loadingStates
    }

//    var movieList: MutableStateFlow<PagingData<Movie>> = MutableStateFlow<PagingData<Movie>>()


//    val allCheeses: Flow<PagingData<Movie>> = Pager(
//        config = PagingConfig(
//            /**
//             * A good page size is a value that fills at least a few screens worth of content on a
//             * large device so the User is unlikely to see a null item.
//             * You can play with this constant to observe the paging behavior.
//             *
//             * It's possible to vary this with list device size, but often unnecessary, unless a
//             * user scrolling on a large device is expected to scroll through items more quickly
//             * than a small device, such as when the large device uses a grid layout of items.
//             */
//            pageSize = 60,
//
//            /**
//             * If placeholders are enabled, PagedList will report the full size but some items might
//             * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
//             *
//             * If placeholders are disabled, onBind will never receive null but as more pages are
//             * loaded, the scrollbars will jitter as new pages are loaded. You should probably
//             * disable scrollbars if you disable placeholders.
//             */
//            enablePlaceholders = true,
//
//            /**
//             * Maximum number of items a PagedList should hold in memory at once.
//             *
//             * This number triggers the PagedList to start dropping distant pages as more are loaded.
//             */
//            maxSize = 200
//        )
//    ) {
//        object : PagingSource<Movie>() {
//
//        }
//    }.flow
//
//    init {
//        fetchData()
//
//
//        val pager = Pager<Int, Movie>(PagingConfig(20)){
//
//        }
//    }
//


//    private fun fetchData() {
//        movieRepository.getPopularMovieList(1)
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//            })
//            .disposeWith(disposables)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        disposables.dispose()
//    }
}