package com.mrn.moviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrn.moviedb.R

/**
 * extension function for setting RecyclerView adapter in xml
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        if (this.adapter == null) {
            this.setHasFixedSize(true)
            this.adapter = adapter
            this.itemAnimator = DefaultItemAnimator()
        }
    }
}

@BindingAdapter("imageUrl")
fun loadImage(
    view: ImageView,
    url: String?
) {
    Glide.with(view.context).load(url)
        .placeholder(R.drawable.ic_movie_poster_placeholder)
        .into(view)
}