package com.ferumate.towatch.movieList.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.data.local.MovieDatabase
import com.ferumate.towatch.commons.testing.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Ferumate on 14.03.2018.
 */
@RunWith(AndroidJUnit4::class)
class MovieListLocalDataTest {
    private lateinit var movieDatabase: MovieDatabase

    private val movieListLocalData: MovieListLocalData by lazy {
        MovieListLocalData(movieDatabase, TestScheduler())
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        movieDatabase = Room
                .inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MovieDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun cleanUp() {
        movieDatabase.close()
    }

    @Test
    fun getMovieList(){
        //given
        val testMovieList = listOf(Movie(1,"","",5f,"","",""))
        movieListLocalData.saveMovieList(testMovieList)

        //when
        val movieListResult = movieListLocalData.getMovieList().test()

        //then
        movieListResult
                .assertNoErrors()
                .assertValue(testMovieList)
    }


}