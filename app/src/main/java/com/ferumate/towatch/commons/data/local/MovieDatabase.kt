package com.ferumate.towatch.commons.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Ferumate on 08.03.2018.
 */
@Database(entities = [Movie::class], version = 3, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
