package com.ferumate.towatch.movieList.model

import com.ferumate.towatch.commons.data.Constants
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.data.remote.MovieListResponse
import com.ferumate.towatch.commons.data.remote.MovieService
import io.reactivex.Flowable

/**
 * Created by Ferumate on 08.03.2018.
 */
class MovieListRemoteData(private val movieService: MovieService): MovieListDataModelContract.Remote {

    override fun getMovieList(): Flowable<MovieListResponse> {
        return movieService.getMovieList(Constants.API_KEY)
    }
}