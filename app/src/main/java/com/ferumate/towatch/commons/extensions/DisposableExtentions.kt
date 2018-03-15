package com.ferumate.towatch.commons.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Ferumate on 08.03.2018.
 */

/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}