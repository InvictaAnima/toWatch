package com.ferumate.towatch.movieList.model

import com.ferumate.towatch.commons.data.Outcome
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.data.remote.MovieListResponse
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Ferumate on 08.03.2018.
 */
interface MovieListDataModelContract {

    interface Repository {
        val outcomeFlow: PublishSubject<Outcome<List<Movie>>>
        fun refreshMovieList()
        fun fetchMovieList()
        fun handleError(e: Throwable)
    }

    interface Local {
        fun getMovieList(): Flowable<List<Movie>>
        fun saveMovieList(movieList: List<Movie>)
    }

    interface Remote {
        fun getMovieList(): Flowable<MovieListResponse>
    }
}