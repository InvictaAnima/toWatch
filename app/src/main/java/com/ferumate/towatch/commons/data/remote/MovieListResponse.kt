package com.ferumate.towatch.commons.data.remote

import com.ferumate.towatch.commons.data.local.Movie
import com.google.gson.annotations.SerializedName

/**
 * Created by Ferumate on 09.03.2018.
 */
data class MovieListResponse(@SerializedName("page") val page: Int,
                             @SerializedName("results") val movieList: List<Movie>)