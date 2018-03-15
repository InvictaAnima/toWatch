package com.ferumate.towatch.commons.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ferumate on 08.03.2018.
 */
@Parcelize
@Entity(tableName = "movie")
data class Movie(@SerializedName("id") @PrimaryKey val id: Long,
                 @SerializedName("title") val title: String,
                 @SerializedName("release_date") val releaseDate: String,
                 @SerializedName("vote_average") val voteAverage: Float,
                 @SerializedName("overview") val overview: String,
                 @SerializedName("poster_path") val posterPath: String,
                 @SerializedName("backdrop_path") val backdropPath: String) : Parcelable

