package com.ferumate.towatch.commons.di

import android.arch.persistence.room.Room
import android.content.Context
import com.ferumate.towatch.commons.data.Constants
import com.ferumate.towatch.commons.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ferumate on 08.03.2018.
 */
@Module
class LocalModule{

    @Singleton
    @Provides
    fun database(context: Context): MovieDatabase = Room.databaseBuilder(context, MovieDatabase::class.java, Constants.DB_NAME).fallbackToDestructiveMigration().build()
}