package com.ferumate.towatch.movieList.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.ferumate.towatch.commons.data.Outcome
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.extensions.toLiveData
import com.ferumate.towatch.movieList.model.MovieListDataModelContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ferumate on 08.03.2018.
 */
class MovieListViewModel(private val repository: MovieListDataModelContract.Repository,
                         private val compositeDisposable: CompositeDisposable): ViewModel(){

    val movieListOutcome: LiveData<Outcome<List<Movie>>> by lazy {
        repository.outcomeFlow.toLiveData(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun init(){
        if(movieListOutcome.value == null){
            repository.fetchMovieList()
        }
    }

    fun refreshMovieList(){
        repository.refreshMovieList()
    }


}
