package com.ferumate.towatch.commons.testing

import android.annotation.SuppressLint
import com.ferumate.towatch.commons.ToWatchApp
import okio.Okio
import java.nio.charset.StandardCharsets

/**
 * Created by Ferumate on 14.03.2018.
 */
object TestUtils {

    @SuppressLint("NewApi")
    fun getResponseFromJson(fileName: String): String {
        val inputStream = ToWatchApp.coreComponent.context().assets.open(fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }
}