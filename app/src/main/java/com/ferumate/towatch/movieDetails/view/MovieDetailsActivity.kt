package com.ferumate.towatch.movieDetails.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ferumate.towatch.R
import com.ferumate.towatch.commons.data.Constants
import com.ferumate.towatch.commons.data.local.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        private const val SELECTED_MOVIE = "selected_movie"

        //Transactions
        private const val TV_MOVIE_TITLE = "tv_movie_title"
        private const val IV_MOVIE_POSTER = "iv_movie_poster"

        fun start(context: Context, movie: Movie, tvTitle: TextView, ivPoster: ImageView) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(SELECTED_MOVIE, movie)

            intent.putExtra(TV_MOVIE_TITLE, ViewCompat.getTransitionName(tvTitle))
            intent.putExtra(IV_MOVIE_POSTER, ViewCompat.getTransitionName(ivPoster))

            val transitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity,
                    Pair(tvTitle as View, ViewCompat.getTransitionName(tvTitle)),
                    Pair(ivPoster as View, ViewCompat.getTransitionName(ivPoster))
            )

            context.startActivity(intent, transitionAnimation.toBundle())
        }
    }

    private val TAG = "MovieDetailsActivity"
    private val context: Context  by lazy { this }

    private var movie: Movie? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setUpToolbar()
        getIntentData()
    }

    private fun setUpToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setCustomView(R.layout.action_bar_custom)
        supportActionBar?.setDisplayShowCustomEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getIntentData() {
        if (!intent.hasExtra(SELECTED_MOVIE)) {
            finish()
            return
        }

        movie = intent.getParcelableExtra(SELECTED_MOVIE)
        tvTitle.text = movie?.title
        Glide.with(context).load(Constants.API_IMAGE_URL + movie?.posterPath).into(ivPoster)

        tvVoteAverage.text = movie?.voteAverage.toString()
        tvReleaseDate.text = movie?.releaseDate
        tvOverview.text = movie?.overview
        Glide.with(context).load(Constants.API_IMAGE_URL + movie?.backdropPath).into(ivBackdrop)

        handleTransition(intent.extras)
    }

    private fun handleTransition(extras: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvTitle.transitionName = extras?.getString(TV_MOVIE_TITLE)
            ivPoster.transitionName = extras?.getString(IV_MOVIE_POSTER)
        }
    }
}
