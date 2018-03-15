package com.ferumate.towatch.commons.testing

import android.support.annotation.VisibleForTesting
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okio.Okio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

/**
 * Created by Ferumate on 14.03.2018.
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object DependencyProvider{

    fun retrofit(baseUrl: HttpUrl): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS).build())
                .build()
    }
}