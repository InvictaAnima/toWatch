package com.ferumate.towatch.movieList.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ferumate.towatch.R
import com.ferumate.towatch.commons.data.Outcome
import com.ferumate.towatch.commons.data.local.Movie
import com.ferumate.towatch.commons.di.HelperDI
import com.ferumate.towatch.movieDetails.view.MovieDetailsActivity
import com.ferumate.towatch.movieList.viewModel.MovieListViewModel
import com.ferumate.towatch.movieList.viewModel.MovieListViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_list.*
import java.io.IOException
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListAdapter.MovieInteractor {
    private val TAG = "MovieListActivity"

    private val component by lazy { HelperDI.movieListComponent() }

    @Inject
    lateinit var viewModelFactory: MovieListViewModelFactory

    @Inject
    lateinit var adapter: MovieListAdapter

    private val viewModel: MovieListViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java) }

    private val context: Context by lazy { this }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setUpToolbar()

        component.inject(this)

        adapter.interactor = this

        rvMovieList.adapter = adapter
        srlMovieList.setOnRefreshListener { viewModel.refreshMovieList() }

        initiateDataListener()

        viewModel.init()
    }

    private fun setUpToolbar() {
        supportActionBar?.setCustomView(R.layout.action_bar_custom)
        supportActionBar?.setDisplayShowCustomEnabled(true)
    }

    private fun initiateDataListener() {
        viewModel.movieListOutcome.observe(this, Observer<Outcome<List<Movie>>> { outcome ->
            when (outcome) {

                is Outcome.Progress -> {
                    srlMovieList.isRefreshing = outcome.loading
                }

                is Outcome.Success -> {
                    adapter.setData(outcome.data)
                    Log.d(TAG, "Outcome.Success")
                }

                is Outcome.Failure -> {
                    Log.e(TAG, outcome.e.message)

                    when (outcome.e) {

                        is IOException -> {
                            Log.d(TAG, "Outcome.Successsssss")
                            Toast.makeText(context, R.string.error_network_message, Toast.LENGTH_LONG).show()
                        }

                        else -> {
                            Log.d(TAG, "Outcomeeeeeee.Success")
                            Toast.makeText(context, R.string.error_message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        })
    }

    override fun movieClicked(movie: Movie, tvTitle: TextView, ivPoster: ImageView) {
        MovieDetailsActivity.start(context, movie, tvTitle, ivPoster)
    }
}
