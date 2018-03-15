package com.ferumate.towatch.movieList.model

import android.util.Log
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.data.local.MovieDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import com.ferumate.towatch.commons.shedulers.Scheduler


/**
 * Created by Ferumate on 08.03.2018.
 */
class MovieListLocalData(private val movieDatabase: MovieDatabase, private val scheduler: Scheduler) : MovieListDataModelContract.Local {

    private val TAG = "MovieListLocalData"

    override fun getMovieList(): Flowable<List<Movie>> {
        return movieDatabase.movieDao().getAll()
    }

    override fun saveMovieList(movieList: List<Movie>) {
        var saveMovieCompletable = Completable.fromAction {
            movieDatabase.movieDao().truncateAndInsert(movieList)
        }

        saveMovieCompletable
                .subscribeOn(scheduler.io())
                .subscribe({}, { Log.e(TAG, it.message) })
    }
}