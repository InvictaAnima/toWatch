package com.ferumate.towatch.commons.di

import android.content.Context
import com.ferumate.towatch.commons.shedulers.DefaultScheduler
import com.ferumate.towatch.commons.shedulers.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ferumate on 08.03.2018.
 */
@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return DefaultScheduler()
    }
}