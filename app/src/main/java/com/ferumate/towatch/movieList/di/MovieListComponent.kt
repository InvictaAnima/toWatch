package com.ferumate.towatch.movieList.di

import com.ferumate.towatch.commons.di.CoreComponent
import com.ferumate.towatch.movieList.view.MovieListActivity
import dagger.Component

/**
 * Created by Ferumate on 08.03.2018.
 */
@MovieListScope
@Component(dependencies = [CoreComponent::class], modules = [MovieListModule::class])
interface MovieListComponent {

    fun inject(movieListActivity: MovieListActivity)
}