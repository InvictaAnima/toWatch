package com.ferumate.towatch.commons.testing

import android.support.annotation.VisibleForTesting
import com.ferumate.towatch.commons.shedulers.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ferumate on 15.03.2018.
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
class TestScheduler : Scheduler {

    override fun mainThread(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }
}