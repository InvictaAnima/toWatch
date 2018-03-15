package com.ferumate.towatch.commons.shedulers

import io.reactivex.Scheduler

/**
 * Created by Ferumate on 15.03.2018.
 */
interface Scheduler {

    fun mainThread(): Scheduler
    fun io(): Scheduler
}
