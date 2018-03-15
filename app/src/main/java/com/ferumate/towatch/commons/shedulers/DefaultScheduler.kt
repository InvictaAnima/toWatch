package com.ferumate.towatch.commons.shedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ferumate on 15.03.2018.
 */
class DefaultScheduler: Scheduler{

    override fun mainThread(): io.reactivex.Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.io()
    }
}