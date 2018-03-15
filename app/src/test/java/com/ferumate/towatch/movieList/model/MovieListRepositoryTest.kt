package com.ferumate.towatch.movieList.model

import com.ferumate.towatch.commons.data.Outcome
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.data.remote.MovieListResponse
import com.ferumate.towatch.commons.testing.TestScheduler
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by Ferumate on 14.03.2018.
 */
@RunWith(RobolectricTestRunner::class)
class MovieListRepositoryTest{
    private val mockedLocal: MovieListDataModelContract.Local = mock()
    private val mockedRemote: MovieListDataModelContract.Remote = mock()

    private lateinit var repository: MovieListRepository
    private val compositeDisposable = CompositeDisposable()

    @Before
    fun init(){
        repository = MovieListRepository(mockedLocal, mockedRemote, TestScheduler(), compositeDisposable)
        whenever(mockedLocal.getMovieList()).doReturn(Flowable.just(emptyList()))
        whenever(mockedRemote.getMovieList()).doReturn(Flowable.just(MovieListResponse(0, emptyList())))
    }

    @Test
    fun casualFetchMovieList(){
        //given
        val testMovieList = listOf(Movie(1,"","",5f,"","",""))
        whenever(mockedLocal.getMovieList()).doReturn(Flowable.just(testMovieList))
        val testObserver = TestObserver<Outcome<List<Movie>>>()
        repository.outcomeFlow.subscribe(testObserver)
        testObserver.assertEmpty()

        //when
        repository.fetchMovieList()

        //then
        verify(mockedLocal).getMovieList()
        testObserver.assertValueAt(0, Outcome.loading(true))
        testObserver.assertValueAt(1, Outcome.loading(false))
        testObserver.assertValueAt(2, Outcome.success(testMovieList))
    }

    @Test
    fun emptyLocalFetchMovieListTriggersRemote(){
        //given
        whenever(mockedLocal.getMovieList()).doReturn(Flowable.just(emptyList()))

        //when
        repository.fetchMovieList()

        //then
        verify(mockedRemote).getMovieList()
    }

    @Test
    fun nonEmptyLocalFetchMoviesNeverTriggersRemote(){
        //given
        val testMovieList = listOf(Movie(1,"","",5f,"","",""))
        whenever(mockedLocal.getMovieList()).doReturn(Flowable.just(testMovieList))

        //when
        repository.fetchMovieList()

        //then
        verify(mockedRemote, never()).getMovieList()
    }

    @Test
    fun refreshTriggersLocalSave(){
        //given
        val testMovieList = listOf(Movie(1,"","",5f,"","",""))
        whenever(mockedRemote.getMovieList()).doReturn(Flowable.just(MovieListResponse(0, testMovieList)))

        //when
        repository.refreshMovieList()

        //then
        verify(mockedLocal).saveMovieList(testMovieList)
    }
}