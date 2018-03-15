package com.ferumate.towatch.movieList.viewModel

import android.arch.lifecycle.Observer
import com.ferumate.towatch.commons.data.Outcome
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.movieList.model.MovieListDataModelContract
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by Ferumate on 14.03.2018.
 */
@RunWith(RobolectricTestRunner::class)
class MovieListViewModelTest{
    private val mockedRepository: MovieListDataModelContract.Repository = mock()
    private val mockedOutcome: Observer<Outcome<List<Movie>>> = mock()

    private lateinit var movieListViewModel: MovieListViewModel

    @Before
    fun init(){
        movieListViewModel = MovieListViewModel(mockedRepository, CompositeDisposable())
        whenever(mockedRepository.outcomeFlow).doReturn(PublishSubject.create())
        movieListViewModel.movieListOutcome.observeForever(mockedOutcome)
    }

    @Test
    fun initiatingViewModelFetchesMovieList(){
        movieListViewModel.init()
        verify(mockedRepository).fetchMovieList()
    }
}