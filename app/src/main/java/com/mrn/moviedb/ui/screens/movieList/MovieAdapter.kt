package com.mrn.moviedb.ui.screens.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrn.core.domain.Movie
import com.mrn.moviedb.databinding.MovieListItemBinding

class MovieAdapter(
//    private var movieList: MutableList<Movie>
) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id
        }
    }

    class MovieViewHolder(val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, movie: Movie) {
            with(binding) {
                this.movie = movie
//                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).let { movie ->
            with(holder) {
                bind(createOnClickListener(binding, movie!!, position), movie)
            }
        }
    }

    private fun createOnClickListener(
        binding: MovieListItemBinding,
        movie: Movie,
        position: Int
    ): View.OnClickListener {
        return View.OnClickListener {
        }
    }
}