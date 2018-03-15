package com.ferumate.towatch.movieList.di

import com.ferumate.towatch.commons.data.local.MovieDatabase
import com.ferumate.towatch.commons.data.remote.MovieService
import com.ferumate.towatch.commons.shedulers.Scheduler
import com.ferumate.towatch.movieList.model.MovieListDataModelContract
import com.ferumate.towatch.movieList.model.MovieListLocalData
import com.ferumate.towatch.movieList.model.MovieListRemoteData
import com.ferumate.towatch.movieList.model.MovieListRepository
import com.ferumate.towatch.movieList.view.MovieListAdapter
import com.ferumate.towatch.movieList.viewModel.MovieListViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Ferumate on 08.03.2018.
 */
@Module
class MovieListModule {

    @Provides
    @MovieListScope
    fun adapter(): MovieListAdapter = MovieListAdapter()

    @Provides
    @MovieListScope
    fun viewModelFactory(repository: MovieListDataModelContract.Repository, compositeDisposable: CompositeDisposable): MovieListViewModelFactory = MovieListViewModelFactory(repository,compositeDisposable)

    @Provides
    @MovieListScope
    fun remoteData(movieService: MovieService): MovieListDataModelContract.Remote = MovieListRemoteData(movieService)

    @Provides
    @MovieListScope
    fun localData(movieDatabase: MovieDatabase, scheduler: Scheduler): MovieListDataModelContract.Local = MovieListLocalData(movieDatabase, scheduler)

    @Provides
    @MovieListScope
    fun repository(local: MovieListDataModelContract.Local, remote: MovieListDataModelContract.Remote, sheduler: Scheduler, compositeDisposable: CompositeDisposable): MovieListDataModelContract.Repository = MovieListRepository(local, remote, sheduler, compositeDisposable)

    @Provides
    @MovieListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()


}