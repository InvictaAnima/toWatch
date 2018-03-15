package com.ferumate.towatch.commons.data.remote

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ferumate on 08.03.2018.
 */
interface MovieService{

    @GET("movie/now_playing")
    fun getMovieList(@Query("api_key") apiKey: String): Flowable<MovieListResponse>
}