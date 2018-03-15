package com.ferumate.towatch.commons

import android.app.Application
import com.ferumate.towatch.commons.di.AppModule
import com.ferumate.towatch.commons.di.CoreComponent
import com.ferumate.towatch.commons.di.DaggerCoreComponent

/**
 * Created by Ferumate on 08.03.2018.
 */
class ToWatchApp : Application() {

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI(){
        coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
    }
}