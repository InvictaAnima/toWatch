package com.ferumate.towatch.commons.di

import android.content.Context
import com.ferumate.towatch.commons.data.local.MovieDatabase
import com.ferumate.towatch.commons.data.remote.MovieService
import com.ferumate.towatch.commons.shedulers.Scheduler
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ferumate on 08.03.2018.
 */
@Singleton
@Component(modules = [AppModule::class, LocalModule::class, RemoteModule::class])
interface CoreComponent {

    fun context(): Context
    fun database(): MovieDatabase
    fun movieService(): MovieService
    fun scheduler(): Scheduler
}