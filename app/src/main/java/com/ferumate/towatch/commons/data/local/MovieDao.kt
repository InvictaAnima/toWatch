package com.ferumate.towatch.commons.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable

/**
 * Created by Ferumate on 08.03.2018.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieList: List<Movie>)

    @Query("DELETE FROM movie")
    fun truncate()

    @Transaction
    fun truncateAndInsert(movieList: List<Movie>){
        truncate()
        insertAll(movieList)
    }

    @Query("SELECT * FROM movie")
    fun getAll(): Flowable<List<Movie>>
}