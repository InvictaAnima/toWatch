package com.ferumate.towatch.commons.di

import com.ferumate.towatch.commons.ToWatchApp
import com.ferumate.towatch.movieList.di.DaggerMovieListComponent
import com.ferumate.towatch.movieList.di.MovieListComponent
import javax.inject.Singleton

/**
 * Created by Ferumate on 08.03.2018.
 */
@Singleton
object HelperDI {
    private var movieListComponent: MovieListComponent? = null
//    private var detailsComponent: DetailsComponent? = null

    fun movieListComponent(): MovieListComponent {
        if (movieListComponent == null)
            movieListComponent = DaggerMovieListComponent.builder().coreComponent(ToWatchApp.coreComponent).build()
        return movieListComponent as MovieListComponent
    }

    fun destroyMovieListComponent() {
        movieListComponent = null
    }
//
//    fun detailsComponent(): DetailsComponent {
//        if (detailsComponent == null)
//            detailsComponent = DaggerDetailsComponent.builder().movieListComponent(movieListComponent()).build()
//        return detailsComponent as DetailsComponent
//    }
//
//    fun destroyDetailsComponent() {
//        detailsComponent = null
//    }
}