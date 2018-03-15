package com.ferumate.towatch.movieList.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ferumate.towatch.movieList.model.MovieListDataModelContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ferumate on 08.03.2018.
 */
@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(private val repository: MovieListDataModelContract.Repository,
                                private val compositeDisposable: CompositeDisposable) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(repository, compositeDisposable) as T
    }
}