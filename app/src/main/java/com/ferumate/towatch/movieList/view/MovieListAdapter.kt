package com.ferumate.towatch.movieList.view

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ferumate.towatch.R
import com.ferumate.towatch.commons.data.Constants
import com.ferumate.towatch.commons.data.local.Movie
import kotlinx.android.synthetic.main.row_movie.view.*

/**
 * Created by Ferumate on 08.03.2018.
 */
class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ListViewHolder>() {

    private var movieList = emptyList<Movie>()
    var interactor: MovieInteractor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(movieList[position])

        if (holder.itemView != null)
            holder.itemView.setOnClickListener { view ->
                with(view) {
                    interactor?.movieClicked(movieList[position], tvTitle, ivPoster)
                }
            }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(data: List<Movie>?) {
        this.movieList = data ?: this.movieList
        notifyDataSetChanged()
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.tvTitle.text = movie.title
            Glide.with(itemView).load(Constants.API_IMAGE_URL + movie.posterPath).into(itemView.ivPoster)

            ViewCompat.setTransitionName(itemView.tvTitle, movie.title)
            ViewCompat.setTransitionName(itemView.ivPoster, movie.posterPath)
        }
    }

    interface MovieInteractor {
            fun movieClicked(movie: Movie, tvTitle: TextView, ivPoster: ImageView)
    }
}