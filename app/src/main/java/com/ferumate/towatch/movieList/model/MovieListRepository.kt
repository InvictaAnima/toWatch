package com.ferumate.towatch.movieList.model

import com.ferumate.towatch.commons.extensions.addTo
import com.ferumate.towatch.commons.data.Outcome
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.extensions.failed
import com.ferumate.towatch.commons.extensions.loading
import com.ferumate.towatch.commons.extensions.success
import com.ferumate.towatch.commons.shedulers.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Ferumate on 08.03.2018.
 */
class MovieListRepository(private val movieListLocalData: MovieListDataModelContract.Local,
                          private val movieListRemoteData: MovieListDataModelContract.Remote,
                          private val scheduler: Scheduler,
                          private val compositeDisposable: CompositeDisposable) : MovieListDataModelContract.Repository {
    private val TAG = "MovieListRepository"

    override val outcomeFlow: PublishSubject<Outcome<List<Movie>>> = PublishSubject.create<Outcome<List<Movie>>>()

    override fun refreshMovieList() {
        outcomeFlow.loading(true)
        movieListRemoteData.getMovieList()
                .observeOn(scheduler.mainThread())
                .subscribeOn(scheduler.io())
                .subscribe({ movieListResponse ->
                    movieListLocalData.saveMovieList(movieListResponse.movieList)
                }, { e -> handleError(e) })
                .addTo(compositeDisposable)
    }

    override fun fetchMovieList() {
        outcomeFlow.loading(true)
        movieListLocalData.getMovieList()
                .observeOn(scheduler.mainThread())
                .subscribeOn(scheduler.io())
                .subscribe({ movieList ->
                    outcomeFlow.success(movieList)
                    if (movieList.isEmpty()) {
                        refreshMovieList()
                    }
                }, { e -> handleError(e) })
                .addTo(compositeDisposable)
    }

    override fun handleError(e: Throwable) {
        outcomeFlow.failed(e)
    }
}